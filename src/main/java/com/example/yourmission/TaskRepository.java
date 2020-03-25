package com.example.yourmission;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDAO mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDAO();
        mAllTasks = mTaskDao.getAlphabetizedTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    void insert(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()-> {
            mTaskDao.insertTask(task);
        });
    }
}
