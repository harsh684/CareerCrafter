# 💼 Career Crafter

**Career Crafter** is a full-stack **Job Portal Web Application** built using **Angular 16**, **Spring Boot**, **MySQL**, and **Bootstrap**. 
The platform connects **Job Seekers** with **Job Recruiters**, offering a seamless experience for posting jobs, managing applications, and applying for opportunities.

---

## 🚀 Features

### 🔐 Authentication
- Secure **JWT-based Authentication & Authorization**
- Role-based access: **Job Seeker** & **Job Recruiter**

### 👤 Job Seeker
- Browse and filter job listings
- Create, edit & upload resumes
- Apply for jobs directly from the portal
- Track application status

### 🧑‍💼 Job Recruiter
- Post new job openings
- View all job applications
- View and select suitable candidates

---

## 🛠️ Tech Stack

| Frontend | Backend | Database | Styling |
|----------|---------|----------|---------|
| [Angular 16](https://angular.io/) | [Spring Boot](https://spring.io/projects/spring-boot) | [MySQL](https://www.mysql.com/) | [Bootstrap](https://getbootstrap.com/) |

- **Authentication:** JWT (JSON Web Token)
- **REST API Integration** between Angular & Spring Boot

---

### Prerequisites
- npm
- Angular CLI
- Java 17+
- Maven
- MySQL
- Bootstrap

---

1️⃣ Backend (Spring Boot)
```bash
cd backend
# Configure application.properties with your MySQL credentials
mvn spring-boot:run

---


2️⃣ Frontend (Angular)
```bash
cd frontend
npm install
ng serve
