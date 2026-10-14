# ✅ TaskFlow — Full-Stack To-Do List Application

> A production-minded, full-stack task manager built with **Spring Boot**, **Spring Data JPA**, **Thymeleaf**, and **MySQL** — engineered around a strict **MVC / layered architecture** with clear separation of concerns across Controller, Service, Repository, and Entity layers.
> 
This project is intentionally scoped to showcase **clean code practices** rather than feature sprawl: every request flows through well-defined layers, business logic is isolated from HTTP concerns, persistence is abstracted behind repository interfaces, and views are rendered server-side with Thymeleaf.

---

## 📌 Table of Contents

1. [Features](#-features)
2. [Tech Stack](#-tech-stack)
3. [Project Architecture](#-project-architecture)
4. [Directory Structure](#-directory-structure)
5. [API Endpoints](#-api-endpoints)
6. [Setup & Installation](#-setup--installation)
7. [Design Decisions](#-design-decisions)

---

## ✨ Features

- **Full CRUD lifecycle** for tasks
  - **Create** — add a new task through a dedicated form view
  - **Read** — list all persisted tasks on a single page
  - **Update** — load an existing task into a pre-populated edit form and save changes
  - **Delete** — remove a task by its unique ID
- **Dynamic view rendering with Thymeleaf** — model attributes are bound to HTML templates server-side, with form binding handled via Spring's `@ModelAttribute`
- **Persistent storage** — all tasks are stored in MySQL through Spring Data JPA, so data survives application restarts
- **Layered MVC architecture** — Controller, Service, Repository, and Entity layers with single-responsibility boundaries
- **Responsive UI** — styled with Bootstrap and custom CSS
- **Zero-boilerplate data access** — no hand-written SQL for standard operations; Spring Data JPA generates the repository implementation at runtime
- **Auto schema management** — Hibernate creates and updates the table on startup (`ddl-auto=update`), so setup requires only an empty database

---

## 🛠 Tech Stack

### Backend
- **Java 17+**
- **Spring Boot** (Spring Web MVC)
- **Spring Data JPA / Hibernate** (ORM)
- **Maven** (build & dependency management)

### Frontend
- **Thymeleaf** (server-side template engine)
- **HTML5 / CSS3**

### Database
- **MySQL 8.x**
- **MySQL Connector/J** (JDBC driver)

---

## 🏗 Project Architecture

The application follows a classic **layered MVC** design. Each layer depends only on the layer directly beneath it.

```text
┌──────────────────────────┐
│   Client / Browser       │   HTTP requests (GET / POST)
└────────────┬─────────────┘
             │
             ▼
┌──────────────────────────┐
│   Controller Layer       │   Routing, request binding (@ModelAttribute,
│   (@Controller)          │   @PathVariable), view resolution
└────────────┬─────────────┘
             │  delegates
             ▼
┌──────────────────────────┐
│   Service Layer          │   Business logic, orchestration
│   (@Service)             │
└────────────┬─────────────┘
             │  calls
             ▼
┌──────────────────────────┐
│   Repository Layer       │   Data access abstraction
│   (JpaRepository)        │   (Spring Data JPA)
└────────────┬─────────────┘
             │  Hibernate / JDBC
             ▼
┌──────────────────────────┐
│   MySQL Database         │   Persistent storage
└──────────────────────────┘

        Entity (TaskEntity) ── maps to the `task` table, used across all layers
        Thymeleaf Templates ── rendered by the Controller's returned view name
```

**Request lifecycle example (`POST /save`):**
Browser submits form → `Controller` binds form fields into `TaskEntity` → `Service` processes the task → `Repository` persists it via JPA → MySQL stores the row → Controller returns the next view.

---

## 📁 Directory Structure

```text
todo-list-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── todo/
│   │   │           ├── TodoApplication.java        # Spring Boot entry point
│   │   │           ├── controller/
│   │   │           │   └── TaskController.java     # Routing & view resolution
│   │   │           ├── service/
│   │   │           │   └── TaskService.java        # Business logic layer
│   │   │           ├── repository/
│   │   │           │   └── TaskRepository.java     # Spring Data JPA repository
│   │   │           └── entity/
│   │   │               └── TaskEntity.java         # JPA entity (task table)
│   │   └── resources/
│   │       ├── templates/                          # Thymeleaf views
│   │       │   ├── index.html                      # Landing page
│   │       │   ├── AddTask.html                    # Create-task form
│   │       │   ├── AllTask.html                    # Task listing
│   │       │   └── UpdateTask.html                 # Edit-task form
│   │       ├── static/
│   │       │   └── css/                            # Custom stylesheets
│   │       └── application.properties              # App & datasource config
│   └── test/
│       └── java/com/todo/                          # Unit / integration tests
├── pom.xml                                         # Maven dependencies
└── README.md
```

---

## 🔗 API Endpoints

The application is served on **`http://localhost:8080`**.

| HTTP Method | Endpoint | Controller Method | Description / Action |
|:-----------:|----------|-------------------|----------------------|
| `GET`  | `/`                | `home()`                      | Renders the landing page (`index`). |
| `GET`  | `/index`           | `index(Model model)`          | Renders the task creation form (`AddTask`). |
| `POST` | `/save`            | `savetask(TaskEntity)`        | Binds the submitted form to a `TaskEntity` via `@ModelAttribute` and persists a new task. |
| `GET`  | `/all-task`        | `getAllTasks(Model model)`    | Fetches every task from the database and renders the task list view. |
| `POST` | `/delete/{id}`     | `deleteTask(long id)`         | Deletes the task identified by the `{id}` path variable. |
| `GET`  | `/edit/{id}`       | `updateTask(long id, Model)`  | Loads the task by `{id}` into the model and renders a pre-populated edit form. |
| `POST` | `/updateExecute`   | `updatedTask(TaskEntity)`     | Binds the edited form to a `TaskEntity` and saves the updated record. |

---

## 🚀 Setup & Installation

### Prerequisites

| Requirement | Version |
|-------------|---------|
| JDK         | 17 or higher |
| Maven       | 3.8+ |
| MySQL       | 8.x |
| Git         | any recent version |

Verify your toolchain:

```bash
java -version
mvn -version
mysql --version
```

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### 2. Create the MySQL database

```bash
mysql -u root -p
```

```sql
CREATE DATABASE todo_db;
EXIT;
```

> Tables are created automatically by Hibernate on first run — no manual schema script required.

### 3. Configure `application.properties`

Open `src/main/resources/application.properties` and set your credentials:

```properties
# ---------- Server ----------
server.port=8080

# ---------- MySQL Datasource ----------
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ---------- JPA / Hibernate ----------
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# ---------- Thymeleaf ----------
spring.thymeleaf.cache=false
```

**Tip:** avoid committing real credentials. Spring Boot resolves environment variables, so you can externalize secrets:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### 4. Build and run

```bash
mvn clean install
mvn spring-boot:run
```

Or run the packaged JAR:

```bash
java -jar target/*.jar
```

### 5. Open the application

Navigate to **[http://localhost:8080](http://localhost:8080)** in your browser.

### Troubleshooting

| Issue | Fix |
|-------|-----|
| `Communications link failure` | Confirm MySQL is running and the port in `spring.datasource.url` is correct. |
| `Access denied for user` | Re-check the username and password in `application.properties`. |
| `Port 8080 already in use` | Change `server.port` or stop the process occupying the port. |
| `Unknown database 'todo_db'` | Create the database as shown in step 2. |

---

## 🧠 Design Decisions

- **Separation of concerns:** controllers handle only HTTP routing and view selection; business rules live in the service layer; persistence is isolated behind the repository interface.
- **Repository abstraction:** extending `JpaRepository` removes boilerplate DAO code and keeps the data layer swappable and testable.
- **Server-side rendering:** Thymeleaf keeps the stack cohesive, with templates resolved directly from controller return values and natural HTML that previews without a running server.
- **Declarative form binding:** `@ModelAttribute` maps form fields straight onto the `TaskEntity`, eliminating manual request parsing.

---

## 👤 Author

**Ramnivas**
Engineering Student · Java & Spring Boot Developer

🔗 GitHub:(https://github.com/rampra4)

---

⭐ If you found this project useful, consider giving it a star!
