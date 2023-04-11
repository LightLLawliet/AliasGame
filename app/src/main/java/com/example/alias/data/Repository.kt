package com.example.alias.data


interface Repository<S, E> {
    suspend fun fetch(): RiddleResult
}