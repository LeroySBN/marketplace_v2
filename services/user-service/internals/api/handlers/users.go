package handlers

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"product-service/internals/databases/models"
	"strings"
	"user_service/internals/utils"

	"gorm.io/gorm"
)

// CreateCustomer creates a new customer
func CreateUser(c *fiber.Ctx) error {
	customer := new(models.Customer)

	// Error check fields
	if err := c.BodyParser(customer); err != nil {
		if _, ok := err.(*json.UnmarshalTypeError); ok {
			if strings.Contains(err.Error(), "name") {
				return c.Status(400).JSON(fiber.Map{"error": "Missing name of type string"})
			}
			if strings.Contains(err.Error(), "phone") {
				return c.Status(400).JSON(fiber.Map{"error": "Missing phone of type string"})
			}
			if strings.Contains(err.Error(), "password") {
				return c.Status(400).JSON(fiber.Map{"error": "Missing password of type string"})
			}
		}
		if strings.Contains(err.Error(), "unexpected end of JSON input") {
			return c.Status(400).JSON(fiber.Map{"error": "Invalid JSON input"})
		}
		if errors.Is(err, fiber.ErrUnprocessableEntity) {
			return c.Status(422).JSON(fiber.Map{"error": "Unprocessable Entity"})
		}
		return c.Status(400).SendString(err.Error())
	}

	if customer.Name == "" {
		return c.Status(400).JSON(fiber.Map{"error": "Missing name"})
	}

	if customer.Phone == "" {
		return c.Status(400).JSON(fiber.Map{"error": "Missing phone"})
	}

	if customer.Password == "" {
		return c.Status(400).JSON(fiber.Map{"error": "Missing password"})
	}

	// Hash the password
	hashedPassword := utils.HashPassword(customer.Password)
	customer.Password = string(hashedPassword)

	// Check if customer already exists
	var existingCustomer models.Customer
	if err := database.DB.Db.Where("phone = ?", customer.Phone).First(&existingCustomer).Error; err == nil {
		return c.Status(400).JSON(fiber.Map{"error": "Customer already exists"})
	}

	// Create the customer in a goroutine
	go func() {
		if err := database.DB.Db.Create(&customer).Error; err != nil {
			// Handle error in goroutine
			// fmt.Println("Error creating customer:", err)
			c.Status(500).JSON(fiber.Map{"error": "Internal server error"})
			return
		}
	}()

	go func() {
		sms.SendSMS(customer.Phone, "Welcome to our e-commerce platform")
	}()

	// return c.Status(200).JSON(customer)
	return c.Status(200).JSON(fiber.Map{"message": "Sign up successful"})
}

func Login(c *fiber.Ctx) error {
	var loginData struct {
		Phone    string `json:"phone"`
		Password string `json:"password"`
	}
	if err := c.BodyParser(&loginData); err != nil {
		if _, ok := err.(*json.UnmarshalTypeError); ok {
			if strings.Contains(err.Error(), "phone") {
				return c.Status(400).JSON(fiber.Map{"error": "Missing phone number of type string"})
			}
			if strings.Contains(err.Error(), "password") {
				return c.Status(400).JSON(fiber.Map{"error": "Missing password of type string"})
			}
		}
		if strings.Contains(err.Error(), "unexpected end of JSON input") {
			return c.Status(400).JSON(fiber.Map{"error": "Invalid JSON input"})
		}
		if errors.Is(err, fiber.ErrUnprocessableEntity) {
			return c.Status(422).JSON(fiber.Map{"error": "Unprocessable Entity"})
		}
		return c.Status(400).JSON(fiber.Map{"error": "Invalid request"})
	}

	if loginData.Phone == "" {
		return c.Status(400).JSON(fiber.Map{"error": "Missing phone"})
	}

	if loginData.Password == "" {
		return c.Status(400).JSON(fiber.Map{"error": "Missing password"})
	}

	// Retrieve user from the database
	user, err := GetUserByPhone(loginData.Phone)

	if err != nil {
		return err
	}

	// Check if the user exists and the password matches
	if user == nil || !utils.CheckPasswordHash(loginData.Password, user.Password) {
		return c.Status(http.StatusUnauthorized).JSON(fiber.Map{"error": "Invalid credentials"})
	}

	// Execute GetAccessToken function to get the access token
	accessToken, err := auth.GetAccessToken()

	// Extract access token from response
	if accessToken == "" {
		fmt.Println("Access token not found in Hydra token creation response")
		return c.Status(500).JSON(fiber.Map{"error": "Internal server error"})
	}

	if err != nil {
		fmt.Println("Error getting access token:", err)
		return c.Status(500).JSON(fiber.Map{"error": "Internal server error"})
	}

	// Set the access token in the response headers
	c.Set("Authorization", "Bearer "+accessToken)

	// Return a success message
	return c.Status(400).JSON(fiber.Map{
		"message":      "Login successful",
		"access_token": accessToken,
		"token_type":   "bearer",
	})
}

// GetCustomer retrieves information of currently authorized user
func GetUser(c *fiber.Ctx) error {
	// Retrieve user information from the context
	user := c.Locals("user").(*models.Customer)

	// Return user information
	return c.JSON(user)
}

func Logout(c *fiber.Ctx) error {
	// customer := new(models.Customer)
	return nil
}
