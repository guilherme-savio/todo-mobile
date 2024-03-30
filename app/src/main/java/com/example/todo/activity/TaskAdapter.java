package com.example.todo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.database.AppDatabase;
import com.example.todo.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks = new ArrayList<>();
    private AppDatabase db;

    public TaskAdapter(AppDatabase db) {
        this.db = db;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewTaskName.setText(currentTask.getTitulo());
        holder.checkBoxTask.setChecked(currentTask.getStatus());

        // Adiciona um listener para o checkbox
        holder.checkBoxTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Atualiza o status da task no banco de dados
                currentTask.setStatus(isChecked);
                updateTask(currentTask);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    private void updateTask(Task task) {
        // Atualiza a task no banco de dados
        if (db != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    db.taskDao().update(task);
                }
            }).start();
        }
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTaskName;
        private CheckBox checkBoxTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.text_view_task_name);
            checkBoxTask = itemView.findViewById(R.id.check_box_task);
        }
    }
}
