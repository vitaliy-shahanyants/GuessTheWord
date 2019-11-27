package ca.vitos.guesstheword.views.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ca.vitos.guesstheword.R
import ca.vitos.guesstheword.databinding.GameFragmentBinding
import ca.vitos.guesstheword.viewmodel.GameViewModel

class GameFragment: Fragment() {

    // The current word
    private var word = ""

    // The current score
    private var score = 0

    private lateinit var wordList: MutableList<String>

    private lateinit var binding: GameFragmentBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,false)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        updateWordText()
        updateScore()
        return binding.root
    }


    private fun onCorrect(){
        viewModel.onCorrect()
        updateScore()
        updateWordText()
    }

    private fun onSkip(){
        viewModel.onSkip()
        updateWordText()
        updateScore()
    }
    private fun updateWordText(){
        binding.wordText.text = viewModel.word
    }
    private fun updateScore(){
        binding.scoreText.text = viewModel.score.toString()
    }
}