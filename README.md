Contact Management System
A web-based Contact Management System built with Java 17, Spring Boot 4.0.6, Spring Security, Spring Data JPA, MySQL, and Thymeleaf. Visitors can submit contact details through a public form, while an authenticated administrator can access a protected dashboard.
Features
- Public contact form with fields for name, email, and message.
- Saves submitted contact records in a MySQL database.
- Uses Spring Data JPA for database persistence.
- Contact entity with an automatically generated ID.
- Admin authentication using Spring Security.
- Password hashing and verification with BCrypt.
- Role-based access control for the admin dashboard and contacts endpoint.
- Custom UserDetailsService that loads users from the database.
- Admin dashboard page with a contacts table.
- Logout support.
Technology Stack
Layer	Technology
Language	Java 17
Backend	Spring Boot 4.0.6, Spring MVC
Security	Spring Security, BCrypt
Persistence	Spring Data JPA, Hibernate
Database	MySQL
View	Thymeleaf, HTML
Frontend assets	CSS, JavaScript
Build tool	Maven
IDE	IntelliJ IDEA (recommended)


Project Structure
contactform/
├── pom.xml
└── src/
    └── main/
        ├── java/com/example/contactform/
        │   ├── ContactformApplication.java
        │   ├── PasswordGenerator.java
        │   ├── config/
        │   │   └── SecurityConfig.java
        │   ├── controller/
        │   │   └── ContactController.java
        │   ├── entity/
        │   │   ├── Contact.java
        │   │   └── User.java
        │   ├── repository/
        │   │   ├── ContactRepository.java
        │   │   └── UserRepository.java
        │   └── service/
        │       └── CustomUserDetailsService.java
        └── resources/
            ├── application.properties
            ├── static/
            │   ├── css/
            │   └── js/
            └── templates/
                ├── index.html
                └── Contacts.html
Prerequisites
Install the following before running the application:
- JDK 17
- Maven (or use the Maven wrapper if one is added to the project)
- MySQL Server
- IntelliJ IDEA or another Java IDE
Database Setup
1. Start your MySQL server.
2. Create the database:
   CREATE DATABASE contact_db;
3. Open src/main/resources/application.properties.
4. Configure your own MySQL username and password. Do not commit real database credentials to a public repository.
   spring.application.name=contactform
   
   spring.datasource.url=jdbc:mysql://localhost:3306/contact_db
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
Hibernate is configured with ddl-auto=update, so it creates or updates the entity tables when the application starts. The Contact and User entities are mapped to database tables.
Create an Admin User
The application loads login details from the users table through CustomUserDetailsService. There is no automatic admin-user seeding in the current source code, so create an admin record before attempting to log in.

Generate a BCrypt hash for a password using the included PasswordGenerator class, or another trusted BCrypt utility. Then insert the user into MySQL, using the generated hash (not the plain-text password):
INSERT INTO users (username, password, role)
VALUES ('admin', 'PASTE_BCRYPT_HASH_HERE', 'ADMIN');
The security configuration uses hasRole("ADMIN"), and Spring Security's roles("ADMIN") convention expects the stored role value to be ADMIN (without the ROLE_ prefix).

Important: Do not use a plain-text password in the database. Keep credentials private and change any sample or development passwords before deployment.
Run the Application
1. Clone or download this repository.
2. Open the project folder containing pom.xml in IntelliJ IDEA.
3. Make sure the project SDK is set to JDK 17.
4. Allow Maven to import and download dependencies.
5. Create the contact_db database and update the MySQL credentials in application.properties.
6. Run ContactformApplication.java.
Alternatively, from the folder containing pom.xml, run:
mvn spring-boot:run
When the application starts successfully, open:
- Contact form: http://localhost:8080/contact
- Admin login: http://localhost:8080/login
- Admin dashboard: http://localhost:8080/dashboard (requires the ADMIN role)
Application Routes
Method	Endpoint	Purpose	Access
GET	/contact	Displays the public contact form	Public
POST	/submit	Saves a submitted contact	Public
GET	/login	Spring Security login page	Public
GET	/dashboard	Displays the contact dashboard	ADMIN
GET	/contacts	Returns all contacts as JSON	ADMIN
POST	/logout	Logs out the current user	Authenticated


How It Works
1. A visitor opens /contact and fills in the contact form.
2. The form submits a POST request to /submit.
3. ContactController creates a Contact object and saves it using ContactRepository.
4. Spring Data JPA persists the record in MySQL.
5. An administrator signs in through Spring Security.
6. CustomUserDetailsService retrieves the user by username from UserRepository.
7. BCrypt verifies the submitted password against the stored hash.
8. An administrator with the ADMIN role can open /dashboard and request the /contacts endpoint.
Main Components
- Contact – JPA entity representing a submitted contact.
- User – JPA entity representing an application user and role.
- ContactRepository – Provides CRUD and database operations for contacts.
- UserRepository – Provides user persistence and username lookup.
- ContactController – Handles the public form, submission, dashboard, and contacts endpoint.
- CustomUserDetailsService – Loads user credentials and authorities for Spring Security.
- SecurityConfig – Configures password encoding, authentication, route authorization, login, and logout.
Notes / Future Improvements
Potential enhancements for a production-ready version:
- Add server-side validation and user-friendly success/error messages.
- Add CSRF protection and configure the form's CSRF token instead of globally disabling CSRF.
- Add pagination, search, and sorting to the contacts dashboard.
- Add contact deletion and editing with appropriate authorization.
- Improve the dashboard styling and verify that the JavaScript correctly loads and renders contact data.
- Add automated tests for controllers, repositories, and security rules.
- Move database credentials to environment variables or a secrets manager.
- Add database migrations (for example, Flyway) for controlled schema changes.
License
No license is specified in the current project. Add a license file if you intend to distribute or reuse the project publicly.
