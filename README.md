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
├── controller # Web controllers (Item, User, Base) ├── service # Business logic layer ├── dao # MyBatis Mappers ├── model # Data models (ItemModel, UserModel, PromoModel) ├── config # Redis, web server, session config ├── static # Frontend: HTML, JS, CSS files ├── resources # Mapper XMLs, application.properties ├── pom.xml # Project config and dependencies
