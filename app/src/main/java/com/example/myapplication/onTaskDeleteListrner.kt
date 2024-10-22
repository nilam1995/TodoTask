package com.example.myapplication

import com.example.myapplication.model.Task

interface OnTaskDeleteListener {
    fun onTaskDelete(task: Task)
}