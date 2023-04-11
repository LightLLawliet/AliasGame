package com.example.alias.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.alias.R
import com.example.alias.sl.ProvideViewModel

class MainActivity : AppCompatActivity(), ShowFragment {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            NavigationStrategy.Replace(StartFragment())
                .navigate(supportFragmentManager, R.id.container)
    }

    override fun show(fragment: Fragment) =
        NavigationStrategy.Replace(fragment).navigate(supportFragmentManager, R.id.container)
}