package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.vitos.guesstheword.views.score.ScoreViewModelFactory

class ScoreViewModel(finalScore:Int): ViewModel() {

    private val _score =  MutableLiveData<Int>()
    val score: LiveData<Int>
        get()  = _score

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.d("ScoreViewModel","Final score is $finalScore")
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }
    fun onPlayAgainComplete(){
        _eventPlayAgain.value = false
    }

}