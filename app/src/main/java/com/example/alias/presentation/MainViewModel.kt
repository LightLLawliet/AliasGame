package com.example.alias.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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
    private val riddleLiveDataWrapper: RiddleLiveDataWrapper,
    private val repository: Repository<RiddleUi, Error>,
    private val toBaseUi: Riddle.Mapper<RiddleUi> = ToBaseUi(),
    dispatchersList: DispatchersList = DispatchersList.Base(),
) : BaseViewModel(dispatchersList = dispatchersList), Observe<RiddleUi> {

    private val blockUi: suspend (RiddleUi) -> Unit = {
        riddleLiveDataWrapper.map(it)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<RiddleUi>) =
        riddleLiveDataWrapper.observe(owner, observer)

    fun getRiddle() {
        handle({
            val result = repository.fetch()
            if (result.isSuccessful())
                result.map(toBaseUi)
            else
                RiddleUi.Failed(result.errorMessage())
        }, blockUi)
    }
}

interface RiddleUiCallback {

    fun provideRiddle(riddle: String)

    fun provideAnswer(answer: String)
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
