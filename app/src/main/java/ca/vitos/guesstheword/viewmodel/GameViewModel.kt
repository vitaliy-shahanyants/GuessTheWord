package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private lateinit var wordList: MutableList<String>



    init {
        _word.value = ""
        _score.value = 0
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
        if (wordList.isEmpty()) {
            onGameFinish()
        }else{
            _word.value = wordList.removeAt(0)
        }

    }

    fun onSkip(){
        if(!wordList.isEmpty()){
            _score.value = (score.value)?.minus(1)
        }
        nextWord()
    }
    fun onCorrect(){
        if(!wordList.isEmpty()){
            _score.value = (score.value)?.plus(1)
        }
        nextWord()
    }

    fun onGameFinish(){
        _eventGameFinish.value = true
    }
    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel","GameViewModel is destroyed")
    }
}