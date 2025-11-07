# portfolio-

This repository contains a small personal portfolio with three components:

- `backend/` — Go (Gin) API that serves portfolio projects and simple minigames endpoints. Listens on port 8080 by default.
- `frontend/` — React app sources (in `frontend/src/`). This repo doesn't include a packaged frontend build or `package.json`, so you'll usually scaffold a small dev project (Vite or CRA) and copy the `src/` folder into it.
- `java-backend/` — Spring Boot (Maven) microservice containing Java minigame services. Uses Java 21 in the provided `pom.xml`.

## Prerequisites

Install the following on your machine before running anything locally:

- Go (1.20+; go.mod lists 1.23) — https://go.dev/doc/install
- Node.js + npm (Node 18+ recommended) — for running the frontend dev server
- Java JDK 21 — required by the Java backend
- Maven or use the included Maven wrapper (`mvnw.cmd` on Windows)
- (Optional) Docker & Docker Compose if you plan to containerize — note: `docker-compose.yml` in this repo is currently empty

## Run the Go backend (development)

Open PowerShell and run:

```powershell
cd "d:\Desktop\portfolio projeect\portfolio-\backend"
go version            # verify Go is installed
go mod download       # get modules
go run .              # starts the server on port 8080
```

Visit http://localhost:8080/api/projects to confirm the server is running.

If you prefer to build a binary:

```powershell
go build -o portfolio-backend.exe
.\portfolio-backend.exe
```

Notes:
- The Go server uses CORS permitting `http://localhost:3000` by default. If your frontend runs on another port, update `backend/main.go` cors config.

## Run the Java backend (Spring Boot)

Open PowerShell and run using the Maven wrapper (recommended):

```powershell
cd "d:\Desktop\portfolio projeect\portfolio-\java-backend"
.\mvnw.cmd -v                # confirm wrapper works
.\mvnw.cmd spring-boot:run   # runs the app (default port 8080)
```

Or build and run the jar:

```powershell
.\mvnw.cmd package -DskipTests
java -jar .\target\java-backend-0.0.1-SNAPSHOT.jar
```

Notes:
- `pom.xml` sets `<java.version>21</java.version>`. Ensure your `JAVA_HOME` points to a JDK 21 installation.
- Spring Boot defaults to port 8080. Because the Go backend also uses 8080 by default, run only one at a time or change one service's port (e.g., set `server.port=8081` in `application.properties`).

## Run the frontend (quick dev setup)

The `frontend/` folder contains `src/` but no `package.json`. To run the frontend locally you can scaffold a small Vite project in the `frontend/` folder and reuse the `src/` files.

From PowerShell:

```powershell
cd "d:\Desktop\portfolio projeect\portfolio-\frontend"
npm create vite@latest . -- --template react
npm install
```

If the scaffold created a `src/` folder you want to keep your existing sources — back up the scaffolded `src/` first, then copy your repository `src/` files into place.

Start the dev server (use port 3000 so it matches the Go backend CORS):

```powershell
npm run dev -- --port 3000
```

Open http://localhost:3000 in your browser. The frontend expects the API at `http://localhost:8080` (see `frontend/src/App.js`). Adjust ports or the backend CORS config as needed.

If you prefer Create React App instead of Vite, use `npx create-react-app .` and then `npm start`.

## Docker / docker-compose

There is a `docker-compose.yml` file in the repository root but it is currently empty. If you want, I can add a `docker-compose.yml` that builds and runs:

- the Go backend (service: `backend`) exposing 8080
- a static frontend container (after building a production bundle)
- the Java backend (service: `java-backend`) on a separate port

Tell me if you'd like a ready-to-run multi-service `docker-compose.yml` and I will add it.

## Ports and collisions

- Default Go backend: 8080
- Default Spring Boot: 8080 (change one to avoid conflict)
- Recommended frontend dev port: 3000 (matches Go CORS config)

## Troubleshooting

- CORS errors: add your frontend origin (e.g., `http://localhost:5173`) to the `AllowOrigins` slice in `backend/main.go`.
- Go modules failing to download: run `go env GOPROXY` or `go mod tidy` and ensure network access.
- Java version mismatch: set `JAVA_HOME` to JDK 21 and run `java -version`.

## Next steps I can help with

- Scaffold the frontend `package.json` and verify `npm run dev` works locally.
- Create a `docker-compose.yml` that runs the services with non-conflicting ports.
- Add a PowerShell start script to run backend + frontend concurrently for development.

If you'd like one of the above, tell me which and I'll implement it.
