package com.test.todorestapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "you must write what to do")
    private String whatToDo;

    private boolean isCompleted = false;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getWhatToDo() {
        return whatToDo;
    }
    public void setWhatToDo(String whatToDo) {
        this.whatToDo = whatToDo;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
