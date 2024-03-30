package com.example.todo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todo.task.Task;
import com.example.todo.task.TaskDao;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}