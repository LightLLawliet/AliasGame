package com.example.alias.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.alias.data.Repository
import com.example.alias.data.ResultCommunication

class MainViewModel(
    private val repository: Repository,
    private val communicationResult: ResultCommunication
) {
    fun init() = repository.init()

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        communicationResult.observe(owner, observer)

    fun newGame() = Unit
}