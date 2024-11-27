package com.JavaProjects.rest.webservices.Restful_Webservices.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    @Valid
    private int id;
    private String username;
    @Size(min = 5,message = "Enter at least 5 characters")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Enter at date in future ")
    LocalDate dueDate= LocalDate.now();
    private boolean completed;

    public Todo() {
    }

    public Todo(int id, String username, String description, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDueDate() {return  dueDate;}

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username +
                ", description='" + description +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                '}';
    }

    public void setId(Integer o) {
    }
}

