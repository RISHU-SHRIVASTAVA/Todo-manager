package com.rishu.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="Todo_jpa")
public class ToDo {
    @jakarta.persistence.Id
    private int Id;
    @Column(name="todo_title",length=100)
    private String title;
    @Column(name="todo_content",length=100)
    private String content;
    @Column(name="todo_status",length=10)
    private String status;
    @Column(name="todo_added_date")
    private Date addedDate;
    @Column(name="todo_todo_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date toDoDate;

    public Date getAddedDate() {
        return addedDate;
    }


    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getToDoDate() {
        return toDoDate;
    }

    public void setToDoDate(Date toDoDate) {
        this.toDoDate = toDoDate;
    }

    public ToDo(int id, String title, String content, String status, Date addedDate, Date toDoDate) {
        this.Id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.addedDate = addedDate;
        this.toDoDate = toDoDate;
    }

    public ToDo(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addedDate=" + addedDate +
                ", toDoDate=" + toDoDate +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
