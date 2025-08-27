# Task Manager Console Application

A menu-driven console application built with core Java to manage tasks.

---
## Project Overview

### Repository Contents
- All source code: `src/main/java/com/taskmanager/`
- Tasks stored between sessions: `src/main/resource/data/tasks.csv`
- Main application executable: `src/main/java/com/taskmanager/Menu.java`

### Features
- **List tasks**: View all tasks, only `COMPLETED`, and only `PENDING` via console output
- **Add tasks**: Input title, description, status (`PENDING` or `COMPLETED`), and due date
- **Update task status**: Change task status between `PENDING` and `COMPLETED`
- **Delete tasks**: Enter task ID to permanently delete
- **Persistent storage**: Tasks are stored and validated in a CSV file (`src/main/resources/data/tasks.csv`) between 
application executions
  - Tasks load automatically on startup and reload via task updates or deletion

---

## Running the Application

### Requirements
- Java 17+ (application developed and tested on OpenJDK 24, but should run on any version >= 17)
- Console / terminal for running the application


### Setup & Run
1. **Clone the repository**:

```bash
git clone https://github.com/gordon-b-max/TaskManager.git
cd TaskManager
```

2. **Run the application**:

```bash
java src/main/java/com/taskmanager/Menu.java
```