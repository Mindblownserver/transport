# Stage 1: Build the Vue.js application
FROM node:latest AS build

# Set working directory
WORKDIR /app

# Install dependencies
COPY package*.json .npmrc ./

RUN npm cache clean --force && \
    rm -rf node_modules package-lock.json && \
    npm install

# Copy the rest of the application code
COPY . .
COPY .env.example ./

# Build the application
RUN npm run build

# Stage 2: Serve the application
FROM nginx:alpine

# Copy built assets from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80 for the application
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
