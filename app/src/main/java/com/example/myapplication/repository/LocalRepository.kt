package com.example.myapplication.repository

import com.example.myapplication.db.TodoDao
import com.example.myapplication.model.Task
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val appDao: TodoDao
) {
    suspend fun AddTask(list: Task) = appDao.insertTask(list)
    suspend fun deleteTaskFromDb(list: Task) = appDao.delete(list)


    suspend fun getTaskList():List<Task> = appDao.getTaskList()


}