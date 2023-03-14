package ru.game.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Player(private val name: String, private val markId: Int) {
    var score: Int = 0
        private set

    fun increaseScore() { score++ }

    //fun getScore(): Int { return score }

    fun getName(): String { return name }

    fun getMark(): Int { return markId }
}