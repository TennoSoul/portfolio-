# Frontend build stage (Vite + React)
FROM node:18-alpine AS builder
WORKDIR /app

# Install build deps
# Use npm install so a lockfile isn't required during image build if none exists
COPY package.json ./
RUN npm install --silent

# Copy source and build
COPY . .
RUN npm run build

# Production stage: serve with nginx
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
