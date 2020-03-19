package com.example.yourmission;


import java.util.Date;

public class Task {
    private String taskName;
    private String descTask;
    private Date timeTask;
    {
        timeTask = new Date();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescTask() {
        return descTask;
    }

    public void setDescTask(String descTask) {
        this.descTask = descTask;
    }

    public String getTanggal(){
        return String.format("%td",this.timeTask);
    }

    public String getBulan(){
        return String.format("%tb",this.timeTask).toUpperCase();
    }

    public String getTahun(){
        return String.format("%tY",this.timeTask);
    }
}

