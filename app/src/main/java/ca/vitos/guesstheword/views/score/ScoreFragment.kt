package ca.vitos.guesstheword.views.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import ca.vitos.guesstheword.R
import ca.vitos.guesstheword.databinding.ScoreFragmentBinding
import ca.vitos.guesstheword.viewmodel.ScoreViewModel

class ScoreFragment: Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.score_fragment,container,false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)

        binding.viewModel = viewModel

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ScoreViewModel::class.java)
        viewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
            viewModel.onPlayAgainComplete()
        })
        
        return binding.root
    }
}