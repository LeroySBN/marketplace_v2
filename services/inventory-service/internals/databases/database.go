package databases

import (
	"fmt"
	"inventory-service/internals/databases/models"
	"log"
	"os"

	"github.com/joho/godotenv"
	"gorm.io/driver/postgres"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
)

var (
	Db *gorm.DB
)

// InitializeDB initializes the database connection and performs migrations

func Connect() {
	var err error
	var db *gorm.DB

	err = godotenv.Load("../.env")
	if err != nil {
		log.Println("Error loading .env file")
	}

	dsn := fmt.Sprintf("host=%s user=%s password=%s dbname=%s port=%s sslmode=disable TimeZone=UTC",
		os.Getenv("DB_HOST"),
		os.Getenv("DB_USER"),
		os.Getenv("DB_PASSWORD"),
		os.Getenv("DB_NAME"),
		os.Getenv("DB_PORT"),
	)
	db, err = gorm.Open(postgres.Open(dsn), &gorm.Config{
		Logger: logger.Default.LogMode(logger.Info),
	})

	if err != nil {
		log.Printf("Failed to connect to Postgres database: %v", err)
		// sqlite database for testing CRUD operations
		log.Println("Connecting to SQLite database for testing CRUD operations")
		// Attempt to connect to SQLite for testing CRUD operations
		db, err = gorm.Open(sqlite.Open("storeDB"), &gorm.Config{})
		if err != nil {
			log.Fatalf("Failed to connect to SQLite database: %v", err)
		}
	}

	log.Println("Connected to database")
	db.Logger = logger.Default.LogMode(logger.Info)

	// Perform auto-migration
	log.Println("Performing auto-migration")
	err = db.AutoMigrate(&models.Product{})
	if err != nil {
		return
	}

	log.Println("Database migration successful")

	Db = db
}

func CheckDBConnection() bool {
	sqlDB, _ := Db.DB()

	err := sqlDB.Ping()
	if err != nil {
		log.Printf("Error pinging database: %v", err)
		return false
	}

	log.Println("Database connection is alive")
	return true
}
