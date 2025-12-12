# Spring Security Role-Based Authentication with MySQL

A hands-on **Spring Boot** project demonstrating **role-based authentication and authorization** using **Spring Security**, **MySQL**, and **BCrypt password encoding**.  

This project includes:

- Public endpoint accessible to everyone (`/public`)
- User-only endpoint (`/hello`) for `USER` role
- Admin-only endpoint (`/admin`) for `ADMIN` role
- Password hashing using BCrypt
- Custom global exception handling for 401 Unauthorized and 403 Forbidden
- Full test coverage with unit and integration tests

---

## Features

| Endpoint     | Access Role    | Description                   |
|-------------|----------------|-------------------------------|
| `/public`    | Public         | Accessible without login      |
| `/hello`     | USER           | Accessible only to USER role  |
| `/admin`     | ADMIN          | Accessible only to ADMIN role |

---

## Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Security 6
- Spring Data JPA
- MySQL
- Maven
- BCrypt Password Encoder
- JUnit 5 + Mockito + MockMvc (for testing)

---

## Setup Instructions

### 1. C
