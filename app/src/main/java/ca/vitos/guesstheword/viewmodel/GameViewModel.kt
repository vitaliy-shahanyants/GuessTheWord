package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    var word = MutableLiveData<String>()

    var score = MutableLiveData<Int>()
        private set

    private lateinit var wordList: MutableList<String>



    init {
        word.value = ""
        score.value = 0
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
            word.value = wordList.removeAt(0)
        }

    }

    fun onSkip(){
        if(!wordList.isEmpty()){
            score.value = (score.value)?.minus(1)
        }
        nextWord()
    }
    fun onCorrect(){
        if(!wordList.isEmpty()){
            score.value = (score.value)?.plus(1)
        }
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel","GameViewModel is destroyed")
    }
}