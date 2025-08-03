# Personal Blogging Platform API

This is an introductory backend project built with **Java Spring Boot** and **PostgreSQL**, designed to simulate the core functionality of a personal blogging platform.

The API exposes endpoints that allow for basic **CRUD operations** on blog posts. It demonstrates how to structure a RESTful service using Spring Boot with persistence handled via Spring Data JPA and PostgreSQL.

This project serves as a foundation for understanding the structure and flow of backend services using Spring Boot. It can easily be extended to include authentication, pagination, and article listing features.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 🧱 Database

The PostgreSQL database stores blog articles with a simple schema including fields such as `id`, `title`, `content`, and `createdAt`. JPA handles the object-relational mapping.

## 🚀 Getting Started

To run the project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/rf-rodrigues95/crud-intro-blog.git
   cd crud-intro-blog ```

2. Configure your application.properties with your PostgreSQL credentials.

3. Build and run:
   ```bash
   ./mvnw spring-boot:run
   ```
