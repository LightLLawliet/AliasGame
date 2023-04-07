package com.example.alias.data

import com.example.alias.R

interface Repository {

    fun isGameOver(): Boolean

    fun checkGameOver(): Boolean

    fun reset()

    fun init()

    fun gameOverMessage(): String

    class Base(private val manageResources: ManageResources) : Repository {

        private var gameOver = false
        private var lastMessage = manageResources.string(R.string.start_game_message)

        override fun init() = Unit

        override fun isGameOver(): Boolean = false

        override fun checkGameOver(): Boolean = false

        override fun gameOverMessage(): String = ""

        override fun reset() {
            gameOver = false
            lastMessage = manageResources.string(R.string.start_game_message)
        }
    }
}