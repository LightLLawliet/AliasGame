package com.example.alias.data.cloud

import com.example.alias.data.cloud.RiddleCloud
import retrofit2.Call
import retrofit2.http.GET

interface RiddleService {

    @GET("random")
    fun fact(): Call<RiddleCloud>
}