package ca.vitos.guesstheword.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    init {
        Log.d("GameViewModel","GameViewModel is created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel","GameViewModel is destroyed")
    }
}