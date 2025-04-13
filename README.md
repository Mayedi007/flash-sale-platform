# ⚡ Flash Sale – High-Concurrency E-Commerce System

**Flash Sale** is a high-performance e-commerce platform for limited-time promotional deals, built with **Spring Boot**, **Redis**, **MySQL**, and a responsive **HTML + Bootstrap + jQuery** frontend. It supports OTP-based registration, secure login, flash sale countdowns, and real-time inventory control.

---

## 🚀 Features

- ✅ OTP-based user registration
- ✅ Secure login with Redis-backed token sessions
- ✅ Product management: title, image, description, price, stock
- ✅ Promo-based flash sale with countdown timer
- ✅ Guava + Redis caching for high-speed data access
- ✅ Clean Bootstrap interface for all pages
- ✅ MyBatis integration with generator support

---

## 🧱 Tech Stack

| Layer          | Technology                    |
|----------------|-------------------------------|
| Backend        | Spring Boot                   |
| Frontend       | HTML, Bootstrap, jQuery       |
| ORM & DB       | MyBatis + MySQL               |
| Caching        | Redis + Guava Cache           |
| Auth & Session | Spring Session + Redis        |
| Build Tool     | Maven                         |

---


## 📁 Project Structure

```text
flash-sale-platform/
├── pom.xml                         # Maven project file
├── README.md                       # Project documentation
├── src/
│   └── main/
│       ├── java/com/flashsaleproject/
│       │   ├── controller/         # REST controllers (Item, User, etc.)
│       │   ├── service/            # Business logic
│       │   ├── dao/                # MyBatis mappers
│       │   ├── model/              # Data models
│       │   ├── config/             # Configuration classes
│       │   └── error/              # Custom exceptions
│       └── resources/
│           ├── static/html/       # Frontend HTML pages
│           ├── static/css/        # CSS files
│           ├── static/js/         # JavaScript files
│           ├── mybatis/           # Mapper XMLs
│           ├── application.properties
│           └── mybatis-generator.xml

