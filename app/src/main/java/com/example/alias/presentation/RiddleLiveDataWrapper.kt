package com.example.alias.presentation

interface RiddleLiveDataWrapper: LiveDataWrapper<RiddleUi> {

    class Base :LiveDataWrapper.Abstract<RiddleUi>(), RiddleLiveDataWrapper
}