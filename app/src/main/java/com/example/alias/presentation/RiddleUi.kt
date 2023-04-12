package com.example.alias.presentation

interface RiddleUi {

    fun showRiddle(riddleUiCallback: RiddleUiCallback)

    abstract class Abstract(
        private val riddle: String,
        private val answer: String
    ) : RiddleUi {

        override fun showRiddle(riddleUiCallback: RiddleUiCallback) = with(riddleUiCallback) {
            provideRiddle(riddle)
            provideAnswer(answer)
        }
    }

    class Base(riddle: String, answer: String) :
        Abstract(riddle, answer)

    class Failed(value: String) : Abstract(value, "")
}
