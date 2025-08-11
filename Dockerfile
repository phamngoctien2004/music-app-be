# =============================================
# STAGE 1: Build JAR từ mã nguồn
# =============================================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy các file cấu hình trước để cache dependency
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy toàn bộ source vào container
COPY src ./src

# Build project
RUN ./mvnw clean package -DskipTests

# =============================================
# STAGE 2: Chạy app từ jar đã build
# =============================================
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
