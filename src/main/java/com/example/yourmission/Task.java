package com.example.yourmission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String rowId;
    private String taskName;
    private String descTask;
    private String sDateTask;
    private Date dateTask;

    public Task(String aTaskName, String aDescTask, String aDateTask) throws ParseException {
        this.taskName = aTaskName;
        this.descTask = aDescTask;
        this.sDateTask = aDateTask;
        this.dateTask   = new SimpleDateFormat("dd-MMM-yyyy").parse(this.sDateTask);
    }

    public Task(String rowId, String aTaskName, String aDescTask, String aDateTask) throws ParseException {
        this.rowId = rowId;
        this.taskName = aTaskName;
        this.descTask = aDescTask;
        this.sDateTask = aDateTask;
        this.dateTask   = new SimpleDateFormat("dd-MMM-yyyy").parse(this.sDateTask);
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getsDateTask() {
        return sDateTask;
    }

    public void setDateTask(String dateTask) throws ParseException {
        this.sDateTask  = dateTask;
        this.dateTask   = new SimpleDateFormat("dd-MMM-yyyy").parse(this.sDateTask);
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
        return String.format("%td",this.dateTask);
    }

    public String getBulan(){
        return String.format("%tb",this.dateTask).toUpperCase();
    }

    public String getTahun(){
        return String.format("%tY",this.dateTask);
    }

}

