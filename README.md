# Quiz Web Application 🎯

## Overview
This is a web-based quiz application built using *Spring Boot, **MySQL, **React.js, **JavaScript, and **CSS. The application features **JWT-based authentication* with *role-based access control, allowing different functionalities for **students* and *quiz creators*.

## Features 🚀
### 1. *User Authentication 🔐*
- *Sign Up & Log In* using JWT authentication.
- Password encryption for secure user credentials.

### 2. *Role-Based Access Control 👥*
- *Students*:
  - Take quizzes.
  - View their quiz results.
- *Quiz Creators*:
  - Create, update, and manage quizzes.

### 3. *Quiz Functionality 📝*
- Multiple quizzes can be created.
- Students can attempt quizzes and submit their responses.
- The system evaluates and displays results after submission.

## Tech Stack 🛠
### *Backend*
- *Spring Boot* - Backend framework for API development.
- *MySQL* - Database for storing user data, quizzes, and results.
- *JWT (JSON Web Token)* - Authentication and authorization.

### *Frontend*
- *React.js* - UI framework for building interactive interfaces.
- *JavaScript (JS)* - Core scripting language.
- *CSS* - Styling for a responsive design.

## API Endpoints 🔗
### *Authentication*
- POST /api/auth/signup → Register a new user.
- POST /api/auth/login → Authenticate user and return JWT.

### *Quiz Management (Protected Routes)*
- POST /api/quizzes → Create a new quiz (Quiz Creator only).
- GET /api/quizzes → Retrieve all quizzes.
- POST /api/quizzes/{id}/submit → Submit answers for a quiz.
- GET /api/results/{userId} → Get student results.

## Future Enhancements 🔮
- Add time-limited quizzes.
- Implement leaderboard functionality.
- Improve UI/UX with better animations and responsiveness.


---
