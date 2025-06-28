package graphql

import (
	"github.com/99designs/gqlgen/graphql/handler"
	"github.com/99designs/gqlgen/graphql/playground"
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/adaptor/v2"
	"inventory-service/internals/api/graphql/generated"
)

func NewGraphQLHandler() fiber.Handler {
	srv := handler.NewDefaultServer(generated.NewExecutableSchema(generated.Config{Resolvers: &Resolver{}}))
	playgroundHandler := playground.Handler("GraphQL playground", "/api/v1/graphql")

	return func(c *fiber.Ctx) error {
		if c.Method() == fiber.MethodGet {
			return adaptor.HTTPHandler(playgroundHandler)(c)
		}
		return adaptor.HTTPHandler(srv)(c)
	}
}
