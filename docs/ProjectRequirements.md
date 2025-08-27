# SAS Coding Assignment

Build a simple command-line Task Manager application using Java 21+ (core Java only)
that demonstrates data persistence, collections, and user interaction through the console.


## Project Requirements

### Technical Stack
* Java 21 or higher - Core Java only (no external frameworks or libraries)
* File I/O (java.io / java.nio.file) for persistence
* Eclipse IDE preferred (but not required)


### Functionality

Implement a menu-driven console application that supports:

* Adding tasks (title, description, due date)
* Listing tasks (all, only completed, or only pending)
* Updating existing tasks - Marking tasks as completed
* Deleting tasks - Store tasks in memory using Java collections (List / Map)
* Persist tasks between runs by saving them to a file (e.g., CSV or simple text format)
* Load tasks from the file on application startup
* Provide clear console output for all operations.


### Deliverables

A public Git repository (e.g., GitHub) containing:

1) All source code - A README.md file with setup instructions, assumptions,
   and how to run/test the app
2) Submit the repository link at least one day before your interview.

Interview Expectations You will have 10–15 minutes to:
* Present your solution
* Walk through your design and implementation (how tasks are modeled, stored, and persisted)
* Discuss any challenges or trade-offs (e.g., why text files vs. database, choice of collections)
* Answer questions about your approach