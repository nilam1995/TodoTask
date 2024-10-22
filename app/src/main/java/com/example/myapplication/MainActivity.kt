package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var et_addTask:EditText
    lateinit var add_btn:Button
    lateinit var rv_tasks:RecyclerView
    var taskList:ArrayList<Task> = ArrayList<Task>()
    private var taskViewModel: TaskViewModel?= null
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskViewModel = ViewModelProvider(this).get<TaskViewModel>(
            TaskViewModel::class.java
        )
        add_btn = findViewById(R.id.add)
        et_addTask = findViewById(R.id.et_addTask)
        rv_tasks = findViewById(R.id.rv_tasks)

        taskAdapter = TaskAdapter(mutableListOf())
        rv_tasks.adapter = taskAdapter

        add_btn.setOnClickListener {
            val taskName = et_addTask.text.toString()
            if (taskName.isNotEmpty()) {
                val task = Task(task = taskName)
                taskAdapter.addTask(task)
                et_addTask.text.clear()
                taskViewModel!!.addTasktoDb(task)
            }
        }

        taskViewModel!!.taskList.observe(this,{
            if(it.size!=0){
                taskAdapter.setData(it)
            }
        })

    }
}