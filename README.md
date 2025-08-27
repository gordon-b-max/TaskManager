# Task Manager Console Application

A menu-driven console application built with core Java to manage tasks.

---
## Features
- **List tasks**: View all tasks, only `COMPLETED`, and only `PENDING` via console output
- **Add tasks**: Input title, description, status (`PENDING` or `COMPLETED`), and due date
- **Update task status**: Change task status between `PENDING` and `COMPLETED`
- **Delete tasks**: Enter task ID to permanently delete
- **Persistent storage**: Tasks are stored and validated in a CSV file (`src/main/resources/data/tasks.csv`) between 
application executions. Tasks load automatically on startup and reload via task updates or deletion.

---

## Project Structure


---

## Running the Application

### Requirements
- Java 17+ (application developed and tested on OpenJDK 24, but should run on any version >= 17)