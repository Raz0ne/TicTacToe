package ru.game.tictactoe

class Square {
    private var player: Player? = null

    fun setPlayer(player: Player) {
        this.player = player
    }

    fun getPlayer(): Player? {
        return player
    }
}