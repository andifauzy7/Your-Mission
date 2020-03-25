package com.example.yourmission;
import android.annotation.SuppressLint;

import java.util.Date;

class Task {

    private long rowId;
    private String taskName;
    private String descTask;
    private Date dateTask;

    // Constructor.
    Task(String aTaskName, String aDescTask, Date aDate) {
        this.taskName = aTaskName;
        this.descTask = aDescTask;
        this.dateTask   = aDate;
    }

    Task(long rowId, String aTaskName, String aDescTask, Date aDateTask) {
        this.rowId = rowId;
        this.taskName = aTaskName;
        this.descTask = aDescTask;
        this.dateTask   = aDateTask;
    }

    // Getter.
    long getRowId() {
        return rowId;
    }

    @SuppressLint("DefaultLocale")
    String getDateTask(){
        return String.format("%td-%tb-%tY", this.dateTask, this.dateTask, this.dateTask);
    }

    String getTaskName() {
        return taskName;
    }

    String getDescTask() {
        return descTask;
    }

    @SuppressLint("DefaultLocale")
    String getTanggal(){
        return String.format("%td",this.dateTask);
    }

    String getBulan(){
        return String.format("%tb",this.dateTask).toUpperCase();
    }

    String getTahun(){
        return String.format("%tY",this.dateTask);
    }
}

