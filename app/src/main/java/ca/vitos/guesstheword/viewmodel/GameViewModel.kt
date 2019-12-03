package ca.vitos.guesstheword.viewmodel

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private val timer: CountDownTimer
    companion object {
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    val wordHint = Transformations.map(word){ word ->
        val randomPosition = (1..word.length).random()
        "Current word has ${word.length} letters \n "+
                "The letter position $randomPosition is " +
                "${word.get(randomPosition-1).toUpperCase()}"
    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private lateinit var wordList: MutableList<String>

    init {
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

        }
        timer.start()
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
            //onGameFinish()
            resetList()
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
        timer.cancel()
        Log.d("GameViewModel","GameViewModel is destroyed")
    }


}