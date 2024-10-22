package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var et_addTask:EditText
    lateinit var add_btn:Button
    var taskList:ArrayList<Task> = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_btn = findViewById(R.id.add)
        et_addTask = findViewById(R.id.et_addTask)


        add_btn.setOnClickListener {
            if(!et_addTask.text.toString().equals("")){
                val task = Task(0,et_addTask.text.toString(), false)
                taskList.add(task)
            }
        }


    }
}