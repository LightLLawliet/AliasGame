package com.example.alias.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.alias.AliasApp
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
        val showButton = view.findViewById<Button>(R.id.showAnswer)
        val textView = view.findViewById<TextView>(R.id.textTitle)
        val titleText = view.findViewById<TextView>(R.id.titleText)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        startButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            titleText.visibility = View.GONE
            textView.visibility = View.GONE
            startButton.text = getString(R.string.riddle_next)
            startButton.visibility = View.GONE
            showButton.visibility = View.GONE
            viewModel.getRiddle()
        }

        val riddleUiCallback = object : RiddleUiCallback {
            override fun provideRiddle(riddle: String) {
                progressBar.visibility = View.GONE
                textView.visibility = View.GONE
                startButton.visibility = View.VISIBLE
                startButton.visibility = View.VISIBLE
                titleText.visibility = View.VISIBLE
                titleText.text = riddle
            }

            override fun provideAnswer(answer: String) {
                showButton.visibility = View.VISIBLE
                showButton.visibility = View.VISIBLE
                textView.text = answer
            }
        }

        showButton.setOnClickListener {
            textView.visibility = View.VISIBLE
        }
        viewModel.observe(this) {
            it.showRiddle(riddleUiCallback)
        }
    }
}
