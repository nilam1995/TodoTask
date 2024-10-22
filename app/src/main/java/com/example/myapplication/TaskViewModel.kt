package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Task
import com.example.myapplication.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val localRepository:LocalRepository):ViewModel() {
    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> get() = _taskList

    init{
        getTaskFromoDb()
    }

    fun addTasktoDb(task:Task) = viewModelScope.launch{
        localRepository.AddTask(task)
    }

    fun deleteTaskFromDb(task:Task) = viewModelScope.launch{
        localRepository.deleteTaskFromDb(task)
        _taskList.value = localRepository.getTaskList()
    }

    fun getTaskFromoDb(): Job = viewModelScope.launch{
        _taskList.value = localRepository.getTaskList()
    }

}