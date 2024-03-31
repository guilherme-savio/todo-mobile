package com.example.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todo.R;
import com.example.todo.database.AppDatabase;
import com.example.todo.task.Task;

public class CreateTaskActivity extends AppCompatActivity {
    private EditText edtTaskName, edtTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        edtTaskName = findViewById(R.id.edt_task_name);
        edtTaskDescription = findViewById(R.id.edt_task_description);
        Button btnSaveTask = findViewById(R.id.btn_save_task);

        btnSaveTask.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String taskName = edtTaskName.getText().toString().trim();
        String taskDescription = edtTaskDescription.getText().toString().trim();

        if (taskName.isEmpty() || taskDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todo-app").allowMainThreadQueries().build();
            Task newTask = new Task(taskName, taskDescription, Boolean.FALSE);
            db.taskDao().insertAll(newTask);
        } catch (Exception e) {
            System.out.println(e.toString());
            Toast.makeText(this, "Erro ao salvar no banco de dados.", Toast.LENGTH_SHORT).show();
        } finally {
            Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();
            edtTaskName.setText("");
            edtTaskDescription.setText("");
            startActivity(new Intent(CreateTaskActivity.this, ListTaskActivity.class));
        }
    }
}
