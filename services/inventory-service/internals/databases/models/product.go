package models

import (
	"gorm.io/gorm"
	"time"
)

type Product struct {
	ID          uint           `json:"id" gorm:"primaryKey"`
	Name        string         `json:"name" validate:"required" gorm:"not null"`
	Description string         `json:"description"`
	// Image       string         `json:"image" gorm:"text;not null;default:null"`
	Image       string         `json:"image" gorm:"text;default:null"`
	Price       float64        `json:"price" validate:"required,gt=0" gorm:"not null"`
	OfferPrice  float64        `json:"offer_price" validate:"gt=0" gorm:"not null;default:0"`
	Stock       int            `json:"stock" validate:"gte=0" gorm:"not null;default:0"`
	Category    string         `json:"category"`
	CreatedAt   time.Time      `json:"created_at"`
	UpdatedAt   time.Time      `json:"updated_at"`
	DeletedAt   gorm.DeletedAt `json:"-" gorm:"index"`
}
