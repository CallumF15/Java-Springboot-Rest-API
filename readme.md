# Business Management Application

Business Management Application

A full-stack business management application built with Spring Boot and PostgreSQL. The application allows users to create, manage, and view business records through a REST API and web interface.

## Features

- Create and manage business records
- View all registered businesses
- Server-side validation for required fields
- Global exception handling with structured error responses
- REST API built with Spring Boot
- PostgreSQL database integration
- Functional API testing with JUnit and RestAssured

## Tech Stack

**Backend:** Java, Spring Boot, Spring Data JPA, Hibernate Validator  
**Frontend:** To be added at a later date.
**Database:** PostgreSQL  
**Testing:** JUnit, RestAssured  
**API Documentation:** OpenAPI / Swagger  

---

### Backend setup


1. Clone the repository:
2. git clone <repository-url>
3. cd backend
4. Configure your database connection in application.yml:
5. spring:
  	datasource:
   	 url: jdbc:postgresql://localhost:5432/your_database
    	username: your_username
    	password: your_password
6. Create the PostgreSQL database.
7. Build the project:
8. ./gradlew build
9. Run the application:
	./gradlew bootRun 
	OR
	Build and run through any IDE 

### PostgreSQL Database

1. Install PostgreSQL.
2. Create a database.
3. Update database credentials in application.yml.
4. Start the application.

Database tables will be generated automatically through JPA/Hibernate.

### FrontEnd  (will change)

1. cd frontend 
2. npm install
3. npm start

Routes included (additional routes coming)
1) ‘list-tasks’  –  see a list of all tasks
2)  ‘tasks’ – lets you create a new task
 
### Running Tests

Run functional tests using Gradle:

1. ./gradlew functional

2. Or run all tests:
 	./gradlew test

API Documentation

### Swagger/OpenAPI documentation is available when the application is running.

Example:

http://localhost:8080/swagger-ui.html

### Status

🚧 Active Development

This project is currently being expanded with additional business management features and frontend improvements.
