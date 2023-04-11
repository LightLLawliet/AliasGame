package com.example.alias.presentation

interface RiddleUi {

    fun show(factUiCallback: FactUiCallback)

    abstract class Abstract(
        private val riddle: String,
        private val answer: String
    ) : RiddleUi {

        override fun show(factUiCallback: FactUiCallback) = with(factUiCallback) {
            provideText("$riddle\n\n$answer")
        }
    }

    class Base(riddle: String, answer: String) :
        Abstract(riddle, answer)

    class Failed(value: String) : Abstract(value, "")
}
