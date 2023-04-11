package com.example.alias.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alias.data.Repository
import com.example.alias.data.Riddle
import com.example.alias.data.ToBaseUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.alias.data.Error
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: Repository<RiddleUi, Error>,
    private val toBaseUi: Riddle.Mapper<RiddleUi> = ToBaseUi(),
    dispatchersList: DispatchersList = DispatchersList.Base(),
) : BaseViewModel(dispatchersList = dispatchersList) {

    private var factUiCallback: FactUiCallback = FactUiCallback.Empty()

    private val blockUi: suspend (RiddleUi) -> Unit = { it.show(factUiCallback) }

    fun init(factUiCallback: FactUiCallback) {
        this.factUiCallback = factUiCallback
    }

    fun getRiddle() {
        handle({
            val result = repository.fetch()
            if (result.isSuccessful())
                result.map(toBaseUi)
            else
                RiddleUi.Failed(result.errorMessage())
        }, blockUi)
    }

    override fun onCleared() {
        super.onCleared()
        factUiCallback = FactUiCallback.Empty()
    }
}

interface FactUiCallback {

    fun provideText(text: String)

    class Empty : FactUiCallback {

        override fun provideText(text: String) = Unit
    }
}

interface DispatchersList {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun io() = Dispatchers.IO

        override fun ui() = Dispatchers.Main
    }
}

abstract class BaseViewModel(
    private val dispatchersList: DispatchersList
) : ViewModel() {
    fun <T> handle(
        blockIo: suspend () -> T,
        blockUi: suspend (T) -> Unit
    ) = viewModelScope.launch(dispatchersList.io()) {
        val result = blockIo.invoke()
        withContext(dispatchersList.ui()) {
            blockUi.invoke(result)
        }
    }
}
