# Lab 10 - Securing Spring (Chapter 4)

This project demonstrates Spring Security implementation based on Chapter 4 of "Spring in Action".

## Project Structure

```
lab10/
├── pom.xml
├── src/main/java/tacos/
│   ├── Lab10Application.java
│   ├── User.java
│   ├── data/
│   │   └── UserRepository.java
│   ├── security/
│   │   ├── SecurityConfig.java
│   │   ├── UserRepositoryUserDetailsService.java
│   │   ├── RegistrationController.java
│   │   └── RegistrationForm.java
│   └── web/
│       ├── WebConfig.java
│       ├── DesignController.java
│       └── OrderController.java
└── src/main/resources/
    ├── application.properties
    └── templates/
        ├── home.html
        ├── login.html
        ├── registration.html
        ├── design.html
        └── orderForm.html
```

## Features Implemented (Per PDF Chapter 4)

### Section 4.1 - Enabling Spring Security
- ✅ Added `spring-boot-starter-security` dependency in pom.xml

### Section 4.2 - Configuring Spring Security

#### Section 4.2.4 - Customizing User Authentication
- ✅ **User.java (Listing 4.5)** - User entity implementing UserDetails interface
- ✅ **UserRepository.java** - JPA repository with `findByUsername()` method
- ✅ **UserRepositoryUserDetailsService.java (Listing 4.6)** - Custom UserDetailsService implementation
- ✅ **PasswordEncoder** - BCryptPasswordEncoder bean configured
- ✅ **RegistrationController.java (Listing 4.7)** - User registration controller
- ✅ **RegistrationForm.java** - Form backing object for registration

### Section 4.3 - Securing Web Requests

#### Section 4.3.1 - Securing Requests
- ✅ `/design` and `/orders/**` require `ROLE_USER` authentication
- ✅ All other requests (`/`, `/**`) are permitted to all users
- ✅ Security rules configured using `authorizeHttpRequests()`

#### Section 4.3.2 - Custom Login Page
- ✅ Custom login page at `/login`
- ✅ `WebConfig.java` - ViewController for login page
- ✅ `login.html` - Thymeleaf login template with error handling

#### Section 4.3.3 - Logging Out
- ✅ Logout configured with `logoutSuccessUrl("/")`
- ✅ Logout form in `home.html` template

#### Section 4.3.4 - CSRF Protection
- ✅ CSRF protection enabled by default
- ✅ All forms use `th:action` for automatic CSRF token inclusion
- ✅ H2 Console excluded from CSRF for development

### Section 4.4 - Knowing Your User
- ✅ `home.html` uses `sec:authentication="name"` to display username
- ✅ `sec:authorize="isAuthenticated()"` for conditional content

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Security 6**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Thymeleaf** with Spring Security extras
- **BCrypt** password encoding

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Steps

1. **Open project in IntelliJ IDEA**

2. **Enable Lombok** (if not already enabled):
   - File → Settings → Plugins → Search "Lombok" → Install
   - File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
   - Check "Enable annotation processing"

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application**: http://localhost:8080

## Testing the Security Features

### 1. Homepage (Unprotected)
- Visit: http://localhost:8080
- Should display welcome page without authentication

### 2. Register a New User
- Visit: http://localhost:8080/register
- Fill in all fields (username, password, fullname, address, phone)
- Click "Register"
- Redirects to login page

### 3. Login
- Visit: http://localhost:8080/login
- Enter registered username and password
- On success, redirects to `/design` (protected page)

### 4. Access Protected Pages (Requires Authentication)
- http://localhost:8080/design - Design taco page
- http://localhost:8080/orders/current - Order form page
- Unauthenticated users are redirected to login

### 5. Logout
- Click "Logout" button on any page
- Session cleared, redirected to homepage

### 6. H2 Database Console
- Visit: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:tacocloud`
- Username: `sa`
- Password: (leave empty)
- Query: `SELECT * FROM taco_user;`

## Security Configuration Details

### Protected URLs
| URL Pattern | Required Role |
|-------------|---------------|
| `/design` | ROLE_USER |
| `/orders/**` | ROLE_USER |
| `/` | Permit All |
| `/**` | Permit All |
| `/login` | Permit All |
| `/register` | Permit All |
| `/h2-console/**` | Permit All |

### Password Storage
- Passwords are encoded using BCrypt before storing in database
- Never stores plain-text passwords

## Key Implementation Notes

1. **User Entity**: Implements `UserDetails` interface from Spring Security, providing:
   - `getAuthorities()` - Returns ROLE_USER
   - `getUsername()`, `getPassword()` - Credentials
   - Account status methods (all return true)

2. **Custom UserDetailsService**: Looks up users from JPA repository during authentication

3. **Registration**: Uses Spring MVC (not Spring Security) for user registration

4. **CSRF Protection**: Automatic with `th:action` in Thymeleaf forms

