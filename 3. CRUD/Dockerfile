# Usar imagen de Java
FROM eclipse-temurin:21-jdk

# Crear directorio dentro del contenedor
WORKDIR /app

# Copiar el .jar generado por Gradle
COPY build/libs/crud-productos-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
