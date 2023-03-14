package ru.game.tictactoe

class Game(val players: Array<Player>) {
    var curPlayerIdx = 0
        private set
    var field = Field(3, 3)
        private set
    var winnerPlayer: Player? = null
        private set
    var isDraw = false
        private set
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
        else if (isDraw)
            finish()
    }

    fun isStarted(): Boolean { return started }
    private fun finish() { finished = true }

    fun isFinished(): Boolean { return finished }

    fun restart() {
        started = false
        isDraw = false
        finished = false
        field.refresh()

        players.reverse()
        curPlayerIdx = 0
    }

    private fun checkWinner(): Player? {
        var currPlayer: Player? = null
        var lastPlayer: Player?
        var successCounter: Int
        var winPossibilities = 8
        var winPossibility: Boolean

        for (i in 0 until field.rows) {
            lastPlayer = null
            successCounter = 1
            winPossibility = true
            for (j in 0 until field.cols) {
                currPlayer = field[i, j].player
                if (currPlayer != null) {
                    if (currPlayer == lastPlayer)
                        successCounter++
                    else if (lastPlayer != null) {
                        winPossibility = false
                        break
                    }
                }
                lastPlayer = currPlayer ?: lastPlayer
            }

            if (successCounter == countToWin)
                return currPlayer
            if (!winPossibility)
                winPossibilities--
        }

        for (j in 0 until field.cols) {
            lastPlayer = null
            successCounter = 1
            winPossibility = true
            for (i in 0 until field.rows) {
                currPlayer = field[i, j].player
                if (currPlayer != null) {
                    if (currPlayer == lastPlayer)
                        successCounter++
                    else if (lastPlayer != null) {
                        winPossibility = false
                        break
                    }
                }
                lastPlayer = currPlayer ?: lastPlayer
            }
            if (successCounter == countToWin)
                return currPlayer
            if (!winPossibility)
                winPossibilities--
        }

        lastPlayer = null
        successCounter = 1
        winPossibility = true
        for (i in 0 until field.rows) {
            currPlayer = field[i, i].player
            if (currPlayer != null) {
                if (currPlayer == lastPlayer)
                    successCounter++
                else if (lastPlayer != null) {
                    winPossibility = false
                    break
                }
            }
            lastPlayer = currPlayer ?: lastPlayer
        }
        if (successCounter == countToWin)
            return currPlayer
        if (!winPossibility)
            winPossibilities--

        lastPlayer = null
        successCounter = 1
        winPossibility = true
        for (i in 0 until field.rows) {
            currPlayer = field[i, field.cols - i - 1].player
            if (currPlayer != null) {
                if (currPlayer == lastPlayer)
                    successCounter++
                else if (lastPlayer != null) {
                    winPossibility = false
                    break
                }
            }
            lastPlayer = currPlayer ?: lastPlayer
        }
        if (successCounter == countToWin)
            return currPlayer
        if (!winPossibility)
            winPossibilities--

        if (winPossibilities == 0)
            isDraw = true

        return null
    }
}