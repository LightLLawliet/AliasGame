package com.example.alias.data

import com.example.alias.presentation.RiddleUi

interface Riddle {
    suspend fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        suspend fun map(
            riddle: String,
            answer: String
        ): T
    }
}

data class FactDomain(
    private val riddle: String,
    private val answer: String
) : Riddle {

    override suspend fun <T> map(mapper: Riddle.Mapper<T>): T =
        mapper.map(riddle, answer)
}

class ToBaseUi : Riddle.Mapper<RiddleUi> {
    override suspend fun map(
        riddle: String,
        answer: String
    ): RiddleUi {
        return RiddleUi.Base(riddle, answer)
    }
}

class ToDomain : Riddle.Mapper<FactDomain> {
    override suspend fun map(
        riddle: String,
        answer: String
    ): FactDomain {
        return FactDomain(riddle, answer)
    }
}