package routes

import (
	"github.com/gofiber/fiber/v2"
	"inventory-service/internals/api/graphql"
	"inventory-service/internals/databases"
	"time"

	"github.com/gofiber/fiber/v2/middleware/cors"
	"github.com/gofiber/fiber/v2/middleware/limiter"
	"github.com/gofiber/fiber/v2/middleware/logger"
)

func SetupRoutes(app *fiber.App) {
	// Health checks
	app.Get("/health", HealthCheck)
	app.Get("/ready", ReadinessCheck)

	// Middleware injection
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

	// GraphQL
	graphqlHandler := graphql.NewGraphQLHandler()
	api.All("/graphql", graphqlHandler)

	// 404 Handler
	app.Use(notFoundHandler)
}

func HealthCheck(c *fiber.Ctx) error {
	return c.Status(200).JSON(fiber.Map{"status": "healthy"})
}

func ReadinessCheck(c *fiber.Ctx) error {
	if databases.CheckDBConnection() {
		return c.Status(200).JSON(fiber.Map{"Postgres": "OK"})
	}
	return c.Status(500).JSON(fiber.Map{"Postgres": "Error"})
}

func notFoundHandler(c *fiber.Ctx) error {
	return c.Status(404).JSON(fiber.Map{"error": "Not found"})
}
