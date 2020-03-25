package com.example.yourmission;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Query("SELECT * from task_table ORDER BY taskId ASC")
    LiveData<List<Task>> getAlphabetizedTasks();

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);
}
