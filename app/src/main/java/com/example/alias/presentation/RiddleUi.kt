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

    data class Base(private val riddle: String, private val answer: String) :
        Abstract(riddle, answer)

    data class Failed(private val value: String) : Abstract(value, "")
}
