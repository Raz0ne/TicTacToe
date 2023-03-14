package ru.game.tictactoe

class Game(val players: Array<Player>) {
    var curPlayerIdx = 0
    var field = Field(3, 3)
    var winnerPlayer: Player? = null
    private val countToWin = 3
    private var started = false
    private var finished = false

    fun makeMove(y: Int, x: Int) {
        field[y, x].player = players[curPlayerIdx]
        started = true

        curPlayerIdx++
        if (curPlayerIdx == players.size)
            curPlayerIdx = 0

        if (field.isFilled())
            finish()

        winnerPlayer = checkWinner()
        if (winnerPlayer != null) {
            winnerPlayer!!.increaseScore()
            finish()
        }
    }

    fun isStarted(): Boolean { return started }
    private fun finish() { finished = true }

    fun isFinished(): Boolean { return finished }

    fun restart() {
        started = false
        finished = false
        field.refresh()

        players.reverse()
        curPlayerIdx = 0
    }

    private fun checkWinner(): Player? {
        var currPlayer: Player? = null
        var lastPlayer: Player?
        var successCounter: Int

        for (i in 0 until field.rows) {
            lastPlayer = null
            successCounter = 1
            for (j in 0 until field.cols) {
                currPlayer = field[i, j].player
                if (currPlayer == lastPlayer && currPlayer != null)
                    successCounter++
                lastPlayer = currPlayer
            }

            if (successCounter == countToWin)
                return currPlayer
        }

        for (j in 0 until field.cols) {
            lastPlayer = null
            successCounter = 1
            for (i in 0 until field.rows) {
                currPlayer = field[i, j].player
                if (currPlayer == lastPlayer && currPlayer != null)
                    successCounter++
                lastPlayer = currPlayer
            }

            if (successCounter == countToWin)
                return currPlayer
        }

        lastPlayer = null
        successCounter = 1
        for (i in 0 until field.rows) {
            currPlayer = field[i, i].player
            if (currPlayer == lastPlayer && currPlayer != null)
                successCounter++
            lastPlayer = currPlayer
        }
        if (successCounter == countToWin)
            return currPlayer

        lastPlayer = null
        successCounter = 1
        for (i in 0 until field.rows) {
            currPlayer = field[i, field.cols - i - 1].player
            if (currPlayer == lastPlayer && currPlayer != null)
                successCounter++
            lastPlayer = currPlayer
        }
        if (successCounter == countToWin)
            return currPlayer

        return null
    }
}