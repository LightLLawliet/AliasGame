package com.example.alias.data

import com.example.alias.presentation.Communication

interface ResultCommunication : Communication<String> {

    class Base : Communication.Base<String>(), ResultCommunication
}