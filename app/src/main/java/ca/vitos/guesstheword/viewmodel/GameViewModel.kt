package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    var word = ""

    var score = 0

    private lateinit var wordList: MutableList<String>



    init {
        resetList()
        nextWord()
        Log.d("GameViewModel","GameViewModel is created")
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

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            word = wordList.get(0)
        }
    }

    fun onSkip(){
        if(!wordList.isEmpty()){
            score--
        }
        nextWord()
    }
    fun onCorrect(){
        if(!wordList.isEmpty()){
            score++
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel","GameViewModel is destroyed")
    }
}