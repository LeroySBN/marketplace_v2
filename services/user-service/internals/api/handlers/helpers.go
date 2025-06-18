package handlers

import (
	"errors"
	"user_service/internals/database/models"

	"gorm.io/gorm"
)

func GetUserByPhone(phone string) (*models.User, error) {
	var user models.User
	if err := database.DB.Db.Where("phone = ?", phone).First(&user).Error; err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			// Return nil if user not found
			return nil, nil
		}
		// Return error for other database errors
		return nil, err
	}
	return &user, nil
}
