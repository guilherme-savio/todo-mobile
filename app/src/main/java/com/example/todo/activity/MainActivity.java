package com.example.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnViewTasks = findViewById(R.id.btnViewTasks);
        Button btnCreateTask = findViewById(R.id.btnCreateTask);

        btnViewTasks.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListTaskActivity.class));
        });

        btnCreateTask.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateTaskActivity.class));
        });
    }
}
