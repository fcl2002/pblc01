# WealthWise - Financial Management & Investment Tracker

WealthWise is a comprehensive full-stack application designed to help users track their investments, manage wallets, and monitor both fixed and variable income assets. It features a modern React frontend and a robust Spring Boot backend.

## 🚀 Features

-   **User Authentication**: Secure sign-up and login using JWT (JSON Web Tokens).
-   **Wallet Management**: Create and manage multiple investment wallets.
-   **Investment Tracking**:
    -   **Fixed Income**: Track profitability, periods, and taxation for fixed assets.
    -   **Variable Income**: Monitor stocks/ETFs using tickers and real-time quotes.
-   **Market Quotes**: Stay updated with asset price fluctuations.
-   **Notifications**: Receive alerts regarding asset changes or system updates.
-   **Responsive UI**: Modern dashboard with dark and light theme support.
-   **API Documentation**: Fully documented REST API using Swagger/OpenAPI.

## 🛠️ Tech Stack

### Backend
-   **Java 17** with **Spring Boot 3.3.5**
-   **Spring Security** (JWT Authentication)
-   **Spring Data JPA** (Hibernate)
-   **MariaDB** (Relational Database)
-   **Lombok** (Boilerplate reduction)
-   **SpringDoc OpenAPI** (Swagger UI)
-   **Validation** (Jakarta Validation)

### Frontend
-   **React 18** with **TypeScript**
-   **Vite** (Build tool)
-   **Styled Components** (CSS-in-JS & Theming)
-   **React Router Dom v6** (Navigation)
-   **Formik & Yup** (Form handling and validation)
-   **React Auth Kit** (Authentication management)
-   **React Icons** (Iconography)

---

## 📂 Project Structure

```text
pblc01/
├── vscode/api/              # Spring Boot Backend
│   ├── src/main/java        # Java Source Code
│   ├── src/main/resources   # Configuration and SQL scripts
│   └── pom.xml              # Maven Dependencies
└── vscode/frontend/         # React Frontend
    ├── src/domains          # Domain-specific components/pages
    ├── src/global           # Shared routes, contexts, and styles
    └── package.json         # Frontend Dependencies
```

---

## ⚙️ Getting Started

### Prerequisites
-   **JDK 17** or higher
-   **Node.js** (v18+ recommended) & **npm** or **yarn**
-   **MariaDB** or **MySQL** instance

### Backend Setup
1.  Navigate to the API folder:
    ```bash
    cd vscode/api
    ```
2.  Configure the database in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mariadb://localhost:3306/wealthwise
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```
3.  Run the application using Maven:
    ```bash
    ./mvnw spring-boot:run
    ```
    *The API will be available at `http://localhost:8080`.*

### Frontend Setup
1.  Navigate to the frontend folder:
    ```bash
    cd vscode/frontend
    ```
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```
    *The app will be available at `http://localhost:5173`.*

---

## 📖 API Documentation

Once the backend is running, you can access the interactive Swagger UI documentation at:
`http://localhost:8080/swagger-ui/index.html`

---

## 📄 License

This project is developed as part of a Software Development Project (PBL). All rights reserved.
