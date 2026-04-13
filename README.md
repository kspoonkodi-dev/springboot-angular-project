# 🎓 Online Learning Platform – Full Stack Application

## 📌 Project Overview

A full-stack **Online Learning Platform** built using **Angular** and **Spring Boot**, designed to provide a secure and scalable environment for managing courses and tutorials.

The application supports **role-based access** where Admins and Instructors can manage content, and users can browse courses.

---

## 🚀 Key Features

* 🔐 JWT-based Authentication
* 👥 Role-based Authorization (Admin / Instructor)
* 👨‍🏫 Instructor Registration & Approval System
* 📚 Course Management (Create, Update, Delete)
* 📝 Tutorial / Content Management
* 🔎 Course Browsing
* 🌐 REST API Integration
* ⚡ Secure frontend-backend communication

---

## 🛠️ Tech Stack

### 🔹 Frontend

* Angular
* TypeScript
* HTML / CSS
* RxJS

### 🔹 Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

## 📁 Project Structure

```plaintext
project-root/
 ├── frontend/   # Angular application
 ├── backend/    # Spring Boot REST API
 └── README.md   # Main documentation
```

---

## 🔐 Authentication & Authorization Flow

1. User logs in via Angular frontend
2. Backend validates credentials
3. JWT token is generated
4. Token is stored on client side
5. Token is sent in API requests (`Authorization: Bearer <token>`)
6. Access is granted based on user roles (Admin / Instructor)

---

## ▶️ How to Run the Application

### 🔹 Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Runs on:

```bash
http://localhost:8080/
```

---

### 🔹 Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

Runs on:

```bash
http://localhost:4200/
```

---

## 🔗 API Communication

* Frontend communicates with backend using REST APIs
* All secured endpoints require JWT token
* Role-based access is enforced at backend

---

## 🧪 Testing

* Frontend: `ng test`
* Backend: `mvn test`

---

## 🌟 Highlights

* Implemented secure authentication using JWT
* Designed RESTful APIs with Spring Boot
* Built responsive UI using Angular
* Applied role-based authorization using Spring Security
* Followed clean architecture and modular design

---

## 📸 Screenshots

*Add screenshots here (Login Page, Dashboard, Course Management, etc.)*

---

## 💡 Future Enhancements

* 🔄 Refresh Token Implementation
* 📧 Email Notifications
* 📊 Dashboard Analytics
* 🌍 Deployment (AWS / Docker)

---

## 👩‍💻 Author

**Poongodi H**
