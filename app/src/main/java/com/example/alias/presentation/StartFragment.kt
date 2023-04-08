package com.example.alias.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.alias.R

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rulesButton = view.findViewById<Button>(R.id.rulesButton)
        val aboutUsButton = view.findViewById<Button>(R.id.aboutUsButton)
        val mainGameButton = view.findViewById<Button>(R.id.startButton)

        aboutUsButton.setOnClickListener {
            NavigationStrategy.Add(AboutUsFragment())
                .navigate(parentFragmentManager, R.id.container)
        }
        rulesButton.setOnClickListener {
            NavigationStrategy.Add(RulesFragment())
                .navigate(parentFragmentManager, R.id.container)
        }
        mainGameButton.setOnClickListener {
            NavigationStrategy.Replace(BridgeFragment())
                .navigate(parentFragmentManager, R.id.container)
        }
    }
}