package ru.game.tictactoe

class Game(private val players: Array<Player>) {
    private val height = 3
    private val width = 3
    private val squareCount = height * width
    private var field = Array(height) { Array(width) { Square() } }
    private var squaresFilledCount = 0
    private var curPlayerIdx = 0
    private var started = false
    private var ended = false

    fun getField(): Array<Array<Square>> { return field }

    fun makeMove(y: Int, x: Int) {
        field[y][x].setPlayer(players[curPlayerIdx])
        squaresFilledCount++
        started = true

        curPlayerIdx += 1
        if (curPlayerIdx == players.size)
            curPlayerIdx = 0
    }

    fun isStarted(): Boolean { return started }

    fun isFilled(): Boolean { return squaresFilledCount == squareCount }

    fun end() { ended = true }

    fun isEnded(): Boolean { return ended }

    fun restart() {
        started = false
        ended = false
        field = Array(height) { Array(width) { Square() } }
        squaresFilledCount = 0

        players.reverse()
        curPlayerIdx = 0
    }
}