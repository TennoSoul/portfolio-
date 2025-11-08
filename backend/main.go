// backend/main.go
package main

import (
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
)

func main() {
	// Trust only local loopback for dev; do NOT set trusted proxies globally in production.
	// We'll set trusted proxies on the engine instance after creating it.

	r := gin.Default()

	// Trust only local loopback for dev; do NOT use gin.SetTrustedProxies([]string{"*"}) in production.
	if err := r.SetTrustedProxies([]string{"127.0.0.1", "::1"}); err != nil {
		// For development, fail fast so the issue is visible; adjust handling for production.
		panic(err)
	}

	// Add CORS middleware
	r.Use(cors.New(cors.Config{
		// Allow common local dev origins so frontend running on other ports won't be blocked.
		AllowOrigins:     []string{"http://localhost:3000", "http://localhost:5173", "http://localhost:3001"},
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

// New: return games list matching API.md
func getGames(c *gin.Context) {
	games := []gin.H{
		{"id": "1", "name": "Memory Match", "description": "Match pairs"},
		{"id": "2", "name": "Tic-Tac-Toe", "description": "Classic game"},
	}
	c.JSON(200, gin.H{"games": games, "status": "success"})
}

// New: accept a score post (simple echo)
func submitScore(c *gin.Context) {
	gameId := c.Param("id")
	var body map[string]interface{}
	if err := c.BindJSON(&body); err != nil {
		c.JSON(400, gin.H{"error": "invalid body"})
		return
	}
	// Echo back with gameId and received body (replace with DB save later)
	c.JSON(200, gin.H{"gameId": gameId, "received": body})
}

// New: simple skills endpoint
func getSkills(c *gin.Context) {
	skills := []string{"Go", "React", "SQL", "Java"}
	c.JSON(200, gin.H{"skills": skills, "status": "success"})
}

// New: websocket stub (return 501 for now)
func handleWebSocket(c *gin.Context) {
	c.JSON(501, gin.H{"error": "websocket not implemented yet"})
}
