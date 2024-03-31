package com.example.todo.task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);

    @Insert
    void insertAll(Task... task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);
}
