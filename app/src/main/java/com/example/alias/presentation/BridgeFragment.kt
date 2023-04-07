package com.example.alias.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alias.R
import kotlin.system.exitProcess

class BridgeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bridge_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val backButton = view.findViewById<TextView>(R.id.backToStartScreen)

        startButton.setOnClickListener {
            NavigationStrategy.Add(MainGameFragment())
                .navigate(parentFragmentManager, R.id.container)
        }
        backButton.setOnClickListener {
            NavigationStrategy.Add(StartFragment())
                .navigate(parentFragmentManager, R.id.container)
        }
    }
}