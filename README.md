# Spring Student Management System

A RESTful API for managing student records built with Spring Boot, Hibernate, and MySQL.

## Setup Instructions

### 1. Database Configuration

Create a `.env` file in the project root directory with your database credentials:

```bash
# Copy the example file
cp .env.example .env
```

Then edit `.env` and add your actual database password:

```
DB_URL=jdbc:mysql://localhost:3306/employee_details
DB_USERNAME=root
DB_PASSWORD=your_actual_password_here
SERVER_PORT=8080
```

**Important:** The `.env` file is in `.gitignore` and will NOT be committed to Git.

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Get All Students
```
GET /api/students
```

### Get Student by ID
```
GET /api/students/{id}
```

### Create Student
```
POST /api/students
Content-Type: application/json

{
  "name": "John Doe",
  "age": 20
}
```

### Update Student
```
PUT /api/students/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "age": 21
}
```

### Delete Student
```
DELETE /api/students/{id}
```

## Project Structure

```
src/main/java/com/naehas/
├── controller/         # REST Controllers
├── service/           # Business Logic
├── repository/        # Data Access Layer
├── model/            # Entity Classes
└── StudentApplication.java
```

## Technologies Used

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- Hibernate 6
- MySQL 8
- Maven
