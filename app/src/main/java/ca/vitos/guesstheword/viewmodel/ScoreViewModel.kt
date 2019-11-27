package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ca.vitos.guesstheword.views.score.ScoreViewModelFactory

class ScoreViewModel(finalScore:Int): ViewModel() {



    val score = finalScore

    init {
        Log.d("ScoreViewModel","Final score is $finalScore")
    }

}