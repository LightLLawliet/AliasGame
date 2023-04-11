package com.example.alias.presentation

import android.content.Context
import androidx.annotation.RawRes


interface ReadRawResource {

    fun read(@RawRes id: Int): String

    class Base(private val context: Context) : ReadRawResource {
        override fun read(id: Int): String =
            context.resources.openRawResource(id).bufferedReader().readText()
    }
}