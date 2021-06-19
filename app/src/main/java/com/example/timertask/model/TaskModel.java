package com.example.timertask.model;

public class TaskModel implements Comparable {
    public  String TaskName;
    public  String TaskTime;

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskTime() {
        return TaskTime;
    }

    public void setTaskTime(String taskTime) {
        TaskTime = taskTime;
    }

    @Override
    public int compareTo(Object o) {

        return 0;
    }
}
