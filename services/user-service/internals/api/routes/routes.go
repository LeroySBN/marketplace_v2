package routes

import (
	"time"
	"user_service/internals/api/handlers"

	"github.com/gofiber/fiber/v2/middleware/cors"
	"github.com/gofiber/fiber/v2/middleware/limiter"
	"github.com/gofiber/fiber/v2/middleware/logger"
)

func SetupRoutes(app *fiber.App) {
	// Middleware
	app.Use(limiter.New(limiter.Config{
		Max:        10,
		Expiration: 1 * time.Minute,
		KeyGenerator: func(c *fiber.Ctx) string {
			return c.IP()
		},
		LimitReached: func(c *fiber.Ctx) error {
			return fiber.NewError(fiber.StatusTooManyRequests, "Rate limit exceeded")
		},
	}))
	app.Use(cors.New())
	app.Use(logger.New())

	// API group
	api := app.Group("/api/v1")

	api.Post("/users", handlers.CreateUser)  // user registration
	api.Post("/users/login", handlers.Login) // user authentication
	api.Get("/users/me", auth.AuthMiddleware(handlers.GetUser))
	api.Post("/users/logout", auth.AuthMiddleware(handlers.Logout))

	// 404 Handler
	app.Use(notFoundHandler)
}

func StatusHandler(c *fiber.Ctx) error {
	if database.CheckDBConnection() {
		return c.Status(200).JSON(fiber.Map{"Postgres": "OK"})
	}
	return c.Status(500).JSON(fiber.Map{"Postgres": "Error"})
}

func notFoundHandler(c *fiber.Ctx) error {
	return c.Status(404).JSON(fiber.Map{"error": "Not found"})
}
