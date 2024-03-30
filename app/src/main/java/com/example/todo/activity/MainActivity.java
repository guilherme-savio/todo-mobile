package com.example.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;

public class MainActivity extends AppCompatActivity {
    private Button btnViewTasks, btnCreateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewTasks = findViewById(R.id.btnViewTasks);
        btnCreateTask = findViewById(R.id.btnCreateTask);

        btnViewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListTaskActivity.class));
            }
        });


        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateTaskActivity.class));
            }
        });
    }
}
