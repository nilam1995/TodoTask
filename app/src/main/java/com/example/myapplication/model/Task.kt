package com.example.myapplication.model

import androidx.room.Entity

@Entity
data class Task(
    val id:Int,
    val task:String,
    val isSelected:Boolean
)
