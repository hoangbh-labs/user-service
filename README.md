# 🚀 User Service - Spring Boot 3 + JWT Authentication

## 📌 Overview

This project is a **3-tier web application** built with Spring Boot, exposing REST APIs for user authentication and authorization.

It demonstrates:

* Secure login using **Spring Security**
* Password hashing with **BCrypt**
* Stateless authentication using **JWT**
* MySQL database integration
* Clean architecture following **3-tier model**

---

## 🏗️ Architecture

### 🔹 Logical Design (3-tier)

```
Client (Postman / Frontend)
        ↓
Controller Layer (REST API)
        ↓
Service Layer (Business Logic)
        ↓
Repository Layer (JPA / Database)
```

---

### 🔹 Physical Deployment

```
[ Client ]
    ↓ HTTP
[ Application Server - Spring Boot ]
    ↓ JDBC
[ MySQL Database ]
```

---

## ⚙️ Tech Stack

* Java 17+
* Spring Boot 3.4.x
* Spring Security 6
* JWT (JSON Web Token)
* MySQL
* Hibernate (JPA)
* Maven

---

## 🔐 Authentication Flow

1. Client sends login request:
```bash
POST api/v1/auth/login
{
  "username": "admin",
  "password": "admin"
}
```

2. Spring Security:

* Authenticates user
* Compares password using BCrypt

3. Server returns:

* JWT token (via cookie or response)

4. Client uses JWT to call protected APIs

---

## 🔑 Password Handling

* Passwords are **hashed using BCrypt**
* No plaintext passwords stored

Example:

```
admin → $2a$10$...
```

⚠️ Important:

* Passwords are NOT decrypted
* Matching is done via `PasswordEncoder.matches()`

---

## 🗄️ Database Configuration

### MySQL setup

```sql
CREATE DATABASE testdb;
```

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Security Configuration Highlights

* Stateless session (JWT-based)
* Custom `UserDetailsService`
* `BCryptPasswordEncoder`
* Custom `JwtFilter`
* Authentication via `AuthenticationManager`


## 🧪 Test API

### Login

```bash
POST api/v1/auth/login
```

Response:

```json
{
  "code": 200,
  "status": "OK",
  "message": "You've been login successfully!",
  "data": [
    {
      "username": "admin",
      "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc3NjUwNzg5MCwiZXhwIjoxNzc2NTExNDkwfQ.jVlWGGFOub1O1OxgOgFfVFPsiseTsxK4syGVsBL8uXw"
    }
  ]
}
```

### Get User info

```bash
POST api/v1/users/1
Header[Authorization:Bearer eyJhbGciOiJIUzI1NiJ....]
```

Response:

```json
{
  "code": 200,
  "status": "OK",
  "message": "Query successfully!",
  "data": [
    {
      "username": "admin",
      "email": "admin@test.com",
      "roles": [
        "ADMIN"
      ]
    }
  ]
}
```

---

## 📦 Project Structure

```
controller/
service/
repository/
payload/
entity/
security/
config/
```

---

## 🧠 Key Learnings

* Spring Security uses **UserDetails**, not entity
* BCrypt is **one-way hashing**
* JWT enables **stateless authentication**
* Proper layering improves maintainability

---

## ⭐ Notes

This project is suitable for:

* Backend interview demo
* Learning Spring Security & JWT
* Understanding real-world authentication flow
