package com.example.alias.data

import com.example.alias.data.cache.RiddleResult


interface Repository<S, E> {
    suspend fun fetch(): RiddleResult
}