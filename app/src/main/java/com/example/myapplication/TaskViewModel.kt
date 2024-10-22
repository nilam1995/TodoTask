package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Task
import com.example.myapplication.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val localRepository:LocalRepository):ViewModel() {

    fun addTasktoDb(task:Task) = viewModelScope.launch{
        localRepository.AddTask(task)
    }

}