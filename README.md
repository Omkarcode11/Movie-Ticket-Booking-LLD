### ⚠️ Note: This is a work-in-progress LLD project. Some features are under active development. 
# 🎬 Movie Ticket Booking App (Java – Low Level Design) 

A **Java-based Low-Level Design (LLD)** implementation of a **Movie Ticket Booking System**.
This project focuses on **core domain modeling**, **seat locking**, **show/screen management**, and **in-memory providers**, making it ideal for **LLD interviews, learning system design, and prototyping**.

---

## ✨ Key Features

* Theatre, screen, show, and seat layout modeling
* In-memory **seat locking** to prevent double bookings during checkout
* Booking lifecycle management with clear status transitions
* Custom exception handling for common booking errors:

  * Seat unavailable
  * Invalid booking state
  * Lock expiration
* Clean, extensible architecture suitable for future enhancements

---

## 🧠 Design Highlights

> 📘 **Deep Dive**: Check out the [ARCHITECTURE.md](ARCHITECTURE.md) for a detailed breakdown of components, flows, and concurrency models.

* Clear separation of concerns:

  * **model** – core domain objects
  * **provider** – abstractions & implementations
  * **exceptions** – custom runtime exceptions
* Small, focused domain classes:

  * `Theatre`, `Screen`, `Show`
  * `Seat`, `SeatLock`
  * `Booking`, `Movie`
* In-memory providers designed for:

  * Easy testing
  * Rapid prototyping
  * Interview-friendly walkthroughs

---

## 📁 Folder Structure

```
src/
 ├── exceptions/     # Custom runtime exceptions
 ├── model/          # Domain models (Booking, Show, Seat, Screen, Theatre, etc.)
 ├── provider/       # Provider interfaces & implementations
 │    ├── ISeatLockProvider.java
 │    └── InMemorySeatLockProvider.java
 └── App.java        # Demo / entry point (if present)

bin/                 # Compiled class files (generated)
lib/                 # External libraries (if any)
```

---

## 🚀 Getting Started

### Prerequisites

* Java **JDK 11+**
* (Optional) IDE: **IntelliJ IDEA** or **VS Code**

---

### Build & Run (Using CLI)

#### 1️⃣ Compile the source code

```powershell
javac -d bin -sourcepath src $(Get-ChildItem -Recurse -Filter "*.java" -Path src | ForEach-Object { $_.FullName })
```

#### 2️⃣ Run the application

```powershell
java -cp bin App
```

> If using an IDE, simply import the project and run `App.java`.

---

## 🧪 How to Explore the Code

* Start with **`src/model`** to understand:

  * Entity relationships
  * Booking and seat lifecycle
* Review **`InMemorySeatLockProvider`** to see:

  * How seats are locked
  * How lock expiry is handled
* Check **exceptions** to understand failure scenarios

---

## 🔧 Possible Enhancements

This project is intentionally simple but extensible.
Some ideas for improvement:

* Add persistent storage (JPA / MySQL / PostgreSQL)
* Redis-based distributed seat locking
* Concurrent booking simulations
* REST APIs using Spring Boot
* Unit & integration tests
* Payment workflow simulation

---

## 🤝 Contributing

Contributions are welcome!

When contributing:

* Keep classes small and focused
* Follow existing package structure
* Add clear commit messages
* Open a PR with a brief explanation of changes

---

## 🏷 Suggested GitHub Repository Metadata

### Short Description

```
Movie Ticket Booking App — Java LLD example with seat locking
```

### Topics / Tags

```
java, low-level-design, lld, system-design, booking-system, seat-locking
```

---

## 📝 Commit Suggestion

When adding this README:

```bash
git add README.md
git commit -m "docs: improve README for movie ticket booking LLD"
git push origin main
```

---

## 📄 License & Contact

Add a `LICENSE` file (MIT / Apache 2.0 recommended)
and update maintainer/contact details if needed.

---

### 💡 Want more?

I can help you:

* Add **LLD explanation diagrams**
* Prepare **interview walkthrough notes**
* Add **Spring Boot REST APIs**
* Create a **CONTRIBUTING.md**
* Optimize this project for **resume & GitHub profile**

Just tell me what you want next 👍
