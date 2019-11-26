package ca.vitos.guesstheword.views.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ca.vitos.guesstheword.R
import ca.vitos.guesstheword.databinding.GameFragmentBinding

class GameFragment: Fragment() {

    // The current word
    private var word = ""

    // The current score
    private var score = 0

    private lateinit var wordList: MutableList<String>

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,false)

        resetList()
        nextWord()

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        return binding.root
    }

    private fun nextWord() {
        if (!wordList.isEmpty()){
            word = wordList.removeAt(0)
        }
        updateWordText()
        updateScore()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun onCorrect(){
        if(!wordList.isEmpty()){
           score++
        }
        nextWord()
    }

    private fun onSkip(){
        if(!wordList.isEmpty()){
            score--
        }
        nextWord()
    }
    private fun updateWordText(){
        binding.wordText.text = word
    }
    private fun updateScore(){
        binding.scoreText.text = score.toString()
    }
}