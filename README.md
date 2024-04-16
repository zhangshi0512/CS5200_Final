# CS5200 Final Project - Online Role-Playing Game Database

Northeastern University, CS5200: Database Management Systems, Spring 2024

Instructor: Richard Cobbe

Group 4: Xin Geng, Boyang Liu, Kun Wang, Shangli Yu, Shi Zhang

## Overview
This project involves the development of a relational database and a Java-based web application for an online role-playing game. The application uses Java Server Pages (JSP), servlets, and JDBC for backend operations, structured according to the MVC pattern.

## Architecture
The project is structured as follows:
- **Model**: Java classes that represent entities in the database.
- **DAO (Data Access Object)**: Classes that handle CRUD operations for each model.
- **Servlet**: Classes that manage HTTP requests and business logic.
- **WebApp**: Contains JSP files and resources for the frontend.

## Setup Instructions

### Prerequisites
- MySQL Server
- Java JDK 8 or higher
- Apache Tomcat
- Eclipse IDE or any Java IDE supporting servlets

### Database Setup
1. Start your MySQL server.
2. Execute the `CS5200Project.sql` script to create and populate the database schema:
   ```bash
   mysql -u [username] -p [database_name] < path_to_sql/CS5200Project.sql
   ```

### Application Configuration
1. Import the project into Eclipse or your preferred IDE.
2. Set up Apache Tomcat as the server in the IDE.
3. Update the database connection settings in `src/main/java/pm3/dao/ConnectionManager.java` to match your MySQL setup.

### Running the Application
- Deploy the application through the IDE onto the Tomcat server.
- Access the web application via `http://localhost:8080/[context-path]/`.

## Key Components
- **Models**: Located in `src/main/java/pm3/model`, representing database entities like Player, Character, Item, etc.
- **DAOs**: Located in `src/main/java/pm3/dao`, these classes provide the interface for database operations.
- **Servlets**: Located in `src/main/java/pm3/servlet`, handling the routing and processing of requests.
- **JSP and Static Files**: Located in `src/main/webapp`, forming the presentation layer.

## Features
- Create, read, update, and delete characters and items.
- Detailed character profiles including jobs, equipment, and inventory.
- Filtering and searching functionalities for players and items.

## License
Refer to the `LICENSE` file for licensing information.
