# 🚀 Spring Boot Backend – Online Learning Platform

## 📌 Overview

This is the backend service for the **Online Learning Platform**, built using **Spring Boot**.
It provides secure REST APIs for managing courses and tutorials with **JWT-based authentication** and **role-based authorization**.

---

## 🔐 Security Features

* JWT-based Authentication
* Role-based Authorization (Admin / Instructor)
* Secure REST endpoints using Spring Security
* Protected APIs with token validation

---

## 🚀 Core Features

* 👤 User Authentication (Login)
* 📚 Course Management (Create, Read, Update, Delete)
* 📝 Tutorial / Content Management
* 👥 Role Management (Admin / Instructor)
* 🌐 REST API design

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

## 📁 Project Structure

```plaintext
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── security/
 └── application.properties
```

---

## 🔐 Authentication Flow

1. User sends login request with credentials
2. Server validates user
3. JWT token is generated
4. Client sends token in header:
   Authorization: Bearer <token>
5. Server validates token for protected APIs

---
## 📡 API Endpoints

### 🔐 Authentication

#### 👨‍🏫 Instructor

| Method | Endpoint                | Description                       |
| ------ | ----------------------- | --------------------------------- |
| POST   | `/instructors/register` | Register a new instructor         |
| POST   | `/login`                | User login & JWT token generation |

---

### 🛠️ Admin

| Method | Endpoint                    | Description                |
| ------ | --------------------------- | -------------------------- |
| GET    | `/admin/all`                | Get all users (Admin only) |
| PUT    | `/admin/approve-instructor` | Approve instructor         |
| PUT    | `/admin/reject-instructor`  | Reject instructor          |

---

### 📚 Courses

| Method | Endpoint            | Description            |
| ------ | ------------------- | ---------------------- |
| GET    | `/courses/all`      | Get all courses        |
| GET    | `/courses/user/all` | Get courses for a user |
| POST   | `/courses/create`   | Create a new course    |
| PUT    | `/courses/update`   | Update course details  |
| DELETE | `/courses/delete`   | Delete a course        |

---

### 📝 Tutorials / Content

| Method | Endpoint                | Description             |
| ------ | ----------------------- | ----------------------- |
| GET    | `/tutorials/all`        | Get all tutorials       |
| GET    | `/tutorials/courseById` | Get tutorials by course |
| POST   | `/tutorials/create`     | Create a tutorial       |
| PUT    | `/tutorials/update`     | Update tutorial         |
| DELETE | `/tutorials/delete`     | Delete tutorial         |

---

## 🔐 Authorization Header

For protected endpoints, include JWT token in request header:

```bash
Authorization: Bearer <your_token>
```

## ▶️ Run the Application

### 1️⃣ Navigate to backend folder

```bash
cd backend
```

### 2️⃣ Run the application

```bash
mvn spring-boot:run
```

---

## 🌐 API Base URL

```bash
http://localhost:8080/
```

---

## 🧪 Testing

```bash
mvn test
```

---

## ⚙️ Configuration

Update database settings in:

```plaintext
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## 📌 Notes

* Ensure MySQL server is running before starting the application
* JWT token must be included in all protected API requests
* Backend should be running before starting the frontend

---

## 👩‍💻 Author

**Poongodi H**
