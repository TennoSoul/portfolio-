# Backend (Go) multi-stage Dockerfile
# Builds a static binary and runs it in a small alpine image

FROM golang:1.20-alpine AS builder
WORKDIR /src

RUN apk add --no-cache git

COPY go.mod go.sum ./
RUN go mod download || true

COPY . .

# Build statically
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o server .

FROM alpine:3.18
RUN apk add --no-cache ca-certificates
WORKDIR /app
COPY --from=builder /src/server .

EXPOSE 8080
ENV PORT=8080

CMD ["/app/server"]
