package com.example.yourmission;
import android.annotation.SuppressLint;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "taskId")
    private long rowId;
    @ColumnInfo(name = "taskName")
    private String taskName;
    @ColumnInfo(name = "descTask")
    private String descTask;
    @ColumnInfo(name = "dateTask")
    private String textDateTask;

    // Constructor.
    public Task(){

    }

    // Setter
    public void setTextDateTask(String textDateTask) {
        this.textDateTask = textDateTask;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescTask(String descTask) {
        this.descTask = descTask;
    }

    // Getter.
    public String getTextDateTask() {
        return textDateTask;
    }

    long getRowId() {
        return rowId;
    }

    String getTaskName() {
        return taskName;
    }

    String getDescTask() {
        return descTask;
    }

    String getTanggal(){
        return textDateTask.substring(0,2);
    }

    String getBulan(){
        return textDateTask.substring(3,6);
    }

    String getTahun(){
        return textDateTask.substring(7);
    }

}

