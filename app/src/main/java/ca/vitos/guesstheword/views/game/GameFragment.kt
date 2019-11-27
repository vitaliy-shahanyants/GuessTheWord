package ca.vitos.guesstheword.views.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import ca.vitos.guesstheword.R
import ca.vitos.guesstheword.databinding.GameFragmentBinding
import ca.vitos.guesstheword.viewmodel.GameViewModel
import ca.vitos.guesstheword.views.score.ScoreFragmentArgs

class GameFragment: Fragment() {

    // The current word
    var word = ""

    // The current score
    var score = 0

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

        binding.viewmodel = viewModel
        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onGameEnd() }

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
    private fun onGameEnd(){
        gameFinished()
    }
    private fun gameFinished(){
        Toast.makeText(activity,"Game Has Finished",Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score)
        NavHostFragment.findNavController(this).navigate(action)

    }
}