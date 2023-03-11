package ru.game.tictactoe

class Player(private val name: String) {
    private var score: Int = 0

    fun increaseScore() {
        score++
    }

    fun getScore(): Int {
        return score
    }

    fun getName(): String {
        return name
    }
}