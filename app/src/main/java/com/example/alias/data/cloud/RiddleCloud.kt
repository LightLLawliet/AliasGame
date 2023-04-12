package com.example.alias.data.cloud

import com.example.alias.data.Riddle
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