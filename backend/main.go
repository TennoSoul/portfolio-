// backend/main.go
package main

import (
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()

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
	c.JSON(200, gin.H{
		"projects": []string{"React Portfolio", "Go API", "Java Minigames"},
	})
}
