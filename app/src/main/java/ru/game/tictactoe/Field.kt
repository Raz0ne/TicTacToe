package ru.game.tictactoe

class Field(val rows: Int, val cols: Int) {
    private var field = Array(rows) { Array(cols) { Square() } }
    private val squareCount = rows * cols
    private var squaresFilledCount = 0

    operator fun set(row: Int, col: Int, player: Player) {
        field[row][col].player = player
        squaresFilledCount++
    }

    operator fun get(row: Int, col: Int): Square { return field[row][col] }

    fun isFilled(): Boolean { return squaresFilledCount == squareCount }

    fun refresh() {
        field = Array(rows) { Array(cols) { Square() } }
        squaresFilledCount = 0
    }
}