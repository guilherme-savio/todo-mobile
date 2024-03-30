package com.example.todo.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todo.R;
import com.example.todo.database.AppDatabase;
import com.example.todo.task.Task;
import com.example.todo.task.TaskDao;

import java.util.List;

public class ListTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        recyclerView = findViewById(R.id.recycler_view_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todo-app").allowMainThreadQueries().build();
            taskAdapter = new TaskAdapter(db);
            recyclerView.setAdapter(taskAdapter);
            loadTasks();
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao carregar tasks do banco de dados.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadTasks() {
        TaskDao taskDao = db.taskDao();
        List<Task> tasks = taskDao.getAll();
        taskAdapter.setTasks(tasks);
    }
}
