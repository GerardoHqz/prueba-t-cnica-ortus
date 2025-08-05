# Prueba Técnica - CRUD con Java Spring Boot

Este proyecto forma parte de una prueba técnica y consiste en un CRUD desarrollado con **Java 21**, **Spring Boot 3.5.4** y **MySQL**. En base al ejercicio propuesto, la API puede crear , leer, actualizar y eliminar productos en una base de datos. Dicho producto cuenta con id, nombre, precio y stock.

---

## 🚀 Pasos para iniciar el proyecto

```bash
# 1. Clonar el repositorio
git clone https://github.com/usuario/proyecto.git
cd proyecto

# 2. Generar el archivo .jar 
./gradlew clean build -x test

# 3. Levantar el entorno con Docker
docker-compose up --build
```

---

## ⚙️ Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.4
- Spring Data JPA
- Spring Validation
- MySQL
- Lombok
- JUnit
- Gradle
- Docker & Docker Compose

---

## 📁 Estructura del proyecto

```
src/
 └─ main/
     ├─ java/
     │   └─ com.ortus.crud_productos/
     │       ├─ controllers/
     │       ├─ models/
     │       ├─ repositories/
     │       ├─ services/
     │       └─ utils/
     └─ resources/
         ├─ application.properties
         └─ ...
Dockerfile
docker-compose.yml
build.gradle
settings.gradle
```

---

## 🧾 Configuración de la base de datos

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_base_de_datos
spring.datasource.username=mi_usuario
spring.datasource.password=mi_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔗 Endpoints disponibles

```http
GET     /productos            # Listar todos los productos
GET     /productos/{id}       # Obtener un producto por ID
POST    /productos            # Crear un nuevo producto
PUT     /productos/{id}       # Actualizar un producto existente
DELETE  /productos/{id}       # Eliminar un producto
```