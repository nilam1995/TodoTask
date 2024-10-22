package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Task

class TaskAdapter(private var taskList: MutableList<Task>,
                  private val listener: OnTaskDeleteListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val taskName: TextView =itemView.findViewById(R.id.tv_task)
        val checkbox: CheckBox =itemView.findViewById(R.id.checkBox)

        fun bind(task: Task) {
            taskName.text = task.task
            checkbox.isChecked = task.isSelected // Bind task selection status

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // Show confirmation dialog when the checkbox is checked
                    showDeleteConfirmationDialog(task)
                }
            }


        }

        private fun showDeleteConfirmationDialog(task: Task) {
            AlertDialog.Builder(itemView.context)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes") { dialog, _ ->
                    listener.onTaskDelete(task)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    checkbox.isChecked = false
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {


        return  TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_task_item,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }



    fun addTask(task: Task) {
        taskList.add(task)
        notifyItemInserted(taskList.size - 1)
    }

    fun setData(it: List<Task>?) {
        if (it != null) {
            taskList.clear()
            taskList.addAll(it)
            notifyDataSetChanged()
        }
    }

}