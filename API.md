# API Documentation

## Endpoints

### GET /api/projects
Returns list of portfolio projects
```json
{
    "projects": [
        {
            "id": "string",
            "title": "string",
            "description": "string",
            "technologies": ["string"]
        }
    ],
    "status": "success"
}
```

### GET /api/games
Returns list of available games
```json
{
    "games": [
        {
            "id": "string",
            "name": "string",
            "description": "string"
        }
    ],
    "status": "success"
}
```