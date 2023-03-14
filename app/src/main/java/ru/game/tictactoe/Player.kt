package ru.game.tictactoe

class Player(val name: String, val markId: Int) {
    var score: Int = 0
        private set

    fun increaseScore() { score++ }
}