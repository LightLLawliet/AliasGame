package com.example.alias.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alias.R

class MainGameFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity?.application as AliasApp).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startButton = view.findViewById<Button>(R.id.startGameButton)
        val textView = view.findViewById<TextView>(R.id.textTitle)
        startButton.setOnClickListener {
            startButton.isEnabled = false
            viewModel.getRiddle()
        }
        val factUiCallback = object : FactUiCallback {
            override fun provideText(text: String) {
                startButton.isEnabled = true
                textView.text = text
                textView.movementMethod
            }
        }
        viewModel.init(factUiCallback)
    }
}
