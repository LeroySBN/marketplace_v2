package models

import (
	"gorm.io/gorm"
	"time"
)

type ProductReview struct {
	ID          uint           `json:"id" gorm:"primaryKey"`
	ProductId   string         `json:"product_id" gorm:"foreignKey"`
	UserId      string         `json:"product_id" gorm:"foreignKey"`
	Name        string         `json:"name" validate:"required" gorm:"not null"`
	Content     string         `json:"content" validate:"required" gorm:"not null"`
	CreatedAt   time.Time      `json:"created_at"`
	DeletedAt   gorm.DeletedAt `json:"-" gorm:"index"`
}
