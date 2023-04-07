package com.example.alias.presentation

import android.app.Application
import com.example.alias.data.ManageResources
import com.example.alias.data.Repository
import com.example.alias.data.ResultCommunication

class AliasApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel =
            MainViewModel(
                Repository.Base(ManageResources.Base(this)),
                ResultCommunication.Base()
            )
    }
}