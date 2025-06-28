package main

import (
	"errors"
	"github.com/gofiber/fiber/v2"
	"github.com/joho/godotenv"
	"inventory-service/internals/api/routes"
	"inventory-service/internals/databases"
	"inventory-service/internals/databases/models"
	"log"
	"os"
)

func main() {
	if err := godotenv.Load(); err != nil {
		log.Println("No .env file found")
	}

	databases.Connect()

	err := databases.Db.AutoMigrate(&models.Product{})
	if err != nil {
		return
	}

	app := fiber.New(fiber.Config{
		ErrorHandler: func(c *fiber.Ctx, err error) error {
			code := fiber.StatusInternalServerError
			var e *fiber.Error
			if errors.As(err, &e) {
				code = e.Code
			}
			return c.Status(code).JSON(fiber.Map{
				"error": err.Error(),
			})
		},
	})

	routes.SetupRoutes(app)

	port := os.Getenv("PORT")
	if port == "" {
		port = "5000"
	}

	log.Printf("Server running on port %s", port)
	log.Fatal(app.Listen(":" + port))
}
