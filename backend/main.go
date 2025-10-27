// backend/main.go
package main

import (
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()

	// Add CORS middleware
	r.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"http://localhost:3000"},
		AllowMethods:     []string{"GET", "POST", "PUT", "DELETE"},
		AllowHeaders:     []string{"Origin", "Content-Type"},
		ExposeHeaders:    []string{"Content-Length"},
		AllowCredentials: true,
	}))

	// Portfolio endpoints
	r.GET("/api/projects", getProjects)
	r.GET("/api/skills", getSkills)

	// Minigame endpoints
	r.GET("/api/games", getGames)
	r.POST("/api/games/:id/score", submitScore)

	// Real-time via WebSocket
	r.GET("/ws", handleWebSocket)

	r.Run(":8080")
}

func getProjects(c *gin.Context) {
	projects := []gin.H{
		{
			"id":           "1",
			"title":        "React Portfolio",
			"description":  "Personal portfolio website",
			"technologies": []string{"React", "JavaScript"},
		},
		{
			"id":           "2",
			"title":        "Go API",
			"description":  "Go API for portfolio",
			"technologies": []string{"Go", "REST"},
		},
		{
			"id":           "3",
			"title":        "Java Minigames",
			"description":  "Java Minigames",
			"technologies": []string{"Java", "Spring"},
		},
	}

	c.JSON(200, gin.H{
		"projects": projects,
		"status":   "success",
	})
}
