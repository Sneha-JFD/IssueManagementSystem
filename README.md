# Issue Management System
A simple Issue Tracking Web Application where users can create, update, delete, and track issues.  
The project includes a complete **Spring Boot backend** and **Angular frontend** with clean UI and full CRUD functionality.

## 🚀 Features
- Create new issues
- View all issues
- Update issue details
- Delete issues
- Search by title or assignee
- Filter by priority or status
- Dashboard summary count
- Auto-set createdDate and updatedDate
- Modern Angular UI (responsive)
- Toast message on success
- Form validations

## 🛠 Technologies Used

### **Backend**
- Java 17+
- Spring Boot
- Hibernate / JPA
- MySQL
- RESTful APIs

### **Frontend**
- Angular
- HTML, CSS, TypeScript
- Angular Forms
- HttpClient

## 📦 Project Setup

### **Backend Setup (Spring Boot)**

1. Import project into STS or Eclipse
2. Create a MySQL database:

   CREATE DATABASE growvenus_task;
  
3. Update `application.properties`:
   
   spring.datasource.url=jdbc:mysql://localhost:3306/growvenus_task
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   
4. Run project  
5. Backend runs on:

   http://localhost:8080
   

### **Frontend Setup (Angular)**

1. Open project in VS Code  
2. Install dependencies:

   npm install
  
3. Run project:

   ng serve --open
   
4. UI opens on:
   
   http://localhost:4200
   

## 📡 API Endpoints

1. Create Issue
POST /issues

2. Get All Issues
GET /issues

3. Get Issue By ID
GET /issues/{id}

4. Update Issue by id
PUT /issues/{id}

5. Update Only Status
PUT /issues/{id}/status

6. Update Full Issue
PUT /issues/{id}/full

7. Delete Issue
DELETE /issues/{id}

8. Filter Issues by priority or status
- Filter by Status: GET /issues/filter?status=OPEN
- Filter by Priority: GET /issues/filter?priority=HIGH
- Filter by Both: GET /issues/filter?priority=HIGH&status=OPEN

9. Search Issues by title or assignee
- Search by Title: GET /issues/search?title=Bug
- Search by Assignee: GET /issues/search?assignee=Sneha
- Search by Both: GET /issues/search?title=Bug&assignee=Sneha

10. Count Issues
GET/issues/count?status

## ✔ Summary

This project meets the assignment requirements:
- Full CRUD  
- Filters  
- Search  
- Dashboard counts  
- Angular UI  
- Spring Boot backend  
- GitHub deliverable  


Share your **GitHub repository link** via email.

# IssueManagementSystem
