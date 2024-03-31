package com.example.todo.activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
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
    private final AppDatabase db;

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

        holder.btnExcluirTask.setOnClickListener(v -> {
            tasks.remove(currentTask);
            notifyItemRemoved(position);
            deleteTask(currentTask);
        });

        holder.checkBoxTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTask.setStatus(isChecked);
            updateTask(currentTask);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    private void updateTask(Task task) {
        if (db != null) {
            new Thread(() -> db.taskDao().update(task)).start();
        }
    }

    private void deleteTask(Task task) {
        if (db != null) {
            new Thread(() -> db.taskDao().delete(task)).start();
        }
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTaskName;
        private final CheckBox checkBoxTask;
        private final ImageButton btnExcluirTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTaskName = itemView.findViewById(R.id.text_view_task_name);
            checkBoxTask = itemView.findViewById(R.id.check_box_task);
            btnExcluirTask = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
