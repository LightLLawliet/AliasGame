package com.example.alias.data

import com.google.gson.annotations.SerializedName

class RiddleCloud(
    @SerializedName("riddle")
    private val riddle: String,
    @SerializedName("answer")
    private val answer: String,
) : Riddle {
    override suspend fun <T> map(mapper: Riddle.Mapper<T>): T =
        mapper.map(riddle, answer)
}