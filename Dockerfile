# =============================================
# STAGE 1: Build JAR từ mã nguồn
# =============================================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy cấu hình Maven trước để cache dependencies
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline

# Copy toàn bộ mã nguồn
COPY src ./src

# Build ra jar
RUN ./mvnw clean package -DskipTests

# =============================================
# STAGE 2: Chạy Spring Boot app
# =============================================
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
