# Business Management Application

A business management application with a Spring Boot backend and a PostgreSQL database. Supports creating, viewing, and validating businesses, with API documentation (to be expanded).

## Features

#task will be removed
- Create tasks with title, description, status, and due date.
- View all tasks.
- Validation for required fields (title, status).
- Global exception handling for proper error responses.
- API documentation using OpenAPI/Swagger annotations.
- Frontend interface for task creation and listing.

#Business
- Create new businesses with title, description... (to be expanded)
- View all businesses
- More to be added

---

## Tech Stack

**Backend:** Java, Spring Boot, Spring Data JPA, Hibernate Validator  
**Frontend:** Nunjucks, Axios  (will change to something else)
**Database:** PostgreSQL  
**Testing:** JUnit, RestAssured  
**API Documentation:** OpenAPI / Swagger  

---

## Setup

Repository contains both frontend and backend.

Open backend with Java
Open Frontend with Visual Studio Code
PostgresSQL will be required to setup with local user/password as well as creating database table task (will include create statement below)

### Backend

note: .ENV file not working just now

1. Clone the repository.
2. Navigate to the backend folder.
3. Configure PostgreSQL connection in or `application.yml`
4. Modify these settings to your PostgreSQL login details with your database with task fields (or configure the .env file)
	DB_NAME=databaseName
	DB_USER_NAME=yourusername
	DB_PASSWORD=yourpassword
5. Make sure to define Poolname too. 
6. Make sure Load / sync the Gradle

### PostgreSQL Database

1. Ensure PostgreSQL is installed.
2. Create database and user or use your existing credentials.
3. Database tables should be generated automatically

### FrontEnd

1) Navigate to Terminal
2) Ensure the directory is correct. Use CD to ensure you are in hmcts-dev-test-frontend-master
3) Install dependencies.

Routes included (additional routes coming)
1) ‘list-tasks’  –  see a list of all tasks
2)  ‘tasks’ – lets you create a new task
 
### Running Tests

The backend includes functional tests to verify API endpoints.

1. Navigate to the backend project folder.
2. Run the functional tests either via accessing Gradle and clicking the run icon text to functional or via command

```bash
./gradlew functional
```
