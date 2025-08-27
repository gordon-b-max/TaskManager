package main.java.com.taskmanager.model;

import java.time.LocalDate;


public class Task {

    private int id;
    private String title;
    private String description;
    private String status; // "PENDING" or "COMPLETED"
    private LocalDate dueDate;


    public Task(int id, String title, String description, String status, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    @Override
    public String toString() {
        return "{ " +
                "id = " + id +
                ", title = '" + title + "'" +
                ", description = '" + description + "'" +
                ", status = '" + status + "'" +
                ", dueDate = " + dueDate +
                " }";
    }
}