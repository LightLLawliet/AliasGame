package com.example.alias.data

import com.google.gson.annotations.SerializedName

class GameData(
    @SerializedName("person")
    val person: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("action")
    val action: String,
)