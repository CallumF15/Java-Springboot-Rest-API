# Business Management Application

A full-stack business management application built with Spring Boot and PostgreSQL, designed to provide a structured platform for creating, managing, and viewing business information through a RESTful API and web interface.

The application allows businesses to be categorised by sector and industry, making it easier to organise and retrieve business records. Each business can store key information including contact details, address information, website details, business descriptions, and associated industry data.

The backend follows a layered architecture, separating controllers, services, repositories, entities, DTOs, and mappers to keep the application maintainable and scalable. MapStruct is used for mapping between entities and DTOs, while validation and global exception handling provide consistent and meaningful API responses.

The REST API is documented using Springdoc OpenAPI / Swagger UI, allowing endpoints to be explored and tested during development.

## Current Features
- Create and manage business records
- Categorise businesses by sector and industry
- Manage business address and country information
- Retrieve individual businesses and business listings
- DTO-based API request and response models
- Input validation for business and contact information
- Global exception handling with meaningful HTTP responses
- Entity-to-DTO mapping using MapStruct
- PostgreSQL database persistence
- Interactive REST API documentation with Swagger UI

## Tech Stack

**Backend:** Java, Spring Boot, Spring Data JPA, Hibernate Validator  
**Frontend:** To be added at a later date

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

### FrontEnd (Will be updated later when added)


 
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
