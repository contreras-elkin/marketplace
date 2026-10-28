# Etapa de compilación: JDK + Maven wrapper
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Dependencias primero, para que Docker las cachee mientras el pom no cambie
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw -q dependency:go-offline

COPY src src
# Los tests necesitan base de datos; se ejecutan fuera del build de la imagen
RUN ./mvnw -q package -DskipTests

# Etapa final: solo el JRE y el jar
FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd --system --no-create-home app
USER app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
