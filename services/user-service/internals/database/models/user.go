package models

import "gorm.io/gorm"

type User struct {
	gorm.Model
	Id       string `gorm:"unique"`
	Name     string `json:"name" gorm:"text;not null;default:null"`
	Phone    string `json:"phone" gorm:"text;not null;unique"`
	Email    string `gorm:"unique"`
	Password string `json:"password" gorm:"text;not null;default:null"`
}
