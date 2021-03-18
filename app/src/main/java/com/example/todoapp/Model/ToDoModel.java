package com.example.todoapp.Model;

//package com.main.todoapp.Model;

public class ToDoModel { //structure d'une tâche (SQLITE)

    //definition des variables
    private int id, status;
    private String task_title, task;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
