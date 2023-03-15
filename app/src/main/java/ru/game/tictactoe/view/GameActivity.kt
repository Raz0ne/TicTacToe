package ru.game.tictactoe.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.game.tictactoe.Game
import ru.game.tictactoe.Player
import ru.game.tictactoe.R
import ru.game.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game
    private lateinit var buttons: Array<Array<ImageButton>>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Game_Activity", "Activity is creating...")
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val playersNames = arrayOf(
            intent.getStringExtra("player1Name")!!,
            intent.getStringExtra("player2Name")!!)

        game = Game(arrayOf(
            Player(if (playersNames[0] != "") playersNames[0] else "X", R.drawable.heart),
            Player(if (playersNames[1] != "") playersNames[1] else "O", R.drawable.star)))

        binding.player1Info.getFragment<PlayerFragment>().setPlayerVm(game.players[0])
        binding.player2Info.getFragment<PlayerFragment>().setPlayerVm(game.players[1])

        binding.backBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.return_to_menu_alert_dialog_title)
            builder.setMessage(R.string.return_to_menu_alert_dialog_message)
            builder.setPositiveButton(android.R.string.ok) { _, _ -> finish()}
            builder.setNegativeButton(android.R.string.cancel) { _, _ -> }
            val alertDialog = builder.create()
            alertDialog.show()
        }

        binding.restartGameBtn.setOnClickListener {
            if (game.finished || !game.started)
                restart()
            else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.restart_alert_dialog_title)
                builder.setMessage(R.string.restart_alert_dialog_message)
                builder.setPositiveButton(android.R.string.ok) { _, _ -> restart()}
                builder.setNegativeButton(android.R.string.cancel) { _, _ -> }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }

        buildGameField()

        onBackPressedDispatcher.addCallback(this, 
            object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val builder = AlertDialog.Builder(applicationContext)
                    builder.setTitle(R.string.return_to_menu_alert_dialog_title)
                    builder.setMessage(R.string.return_to_menu_alert_dialog_message)
                    builder.setPositiveButton(android.R.string.ok) { _, _ -> finish() }
                    builder.setNegativeButton(android.R.string.cancel) { _, _ -> }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            }
        )

        setContentView(binding.root)
        Log.d("Game_Activity", "Activity is created")
    }

    private fun buildGameField() {
        buttons = Array(3) { Array(3) { ImageButton(this) }}

        for (i in 0 until game.field.rows) {
            val row = TableRow(this)
            for (j in 0 until game.field.cols) {
                val button = ImageButton(this)
                buttons[i][j] = button
                button.setOnClickListener {
                    if (game.field[i, j].player == null && !game.finished) {
                        buttons[i][j].setImageResource(game.players[game.curPlayerIdx].markId)
                        makeMove(i, j)
                    }
                }
                row.addView(button, TableRow.LayoutParams(
                    binding.field.layoutParams.width / 3,
                    binding.field.layoutParams.height / 3
                ))
            }
            binding.field.addView(row, TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT ))
        }
    }

    private fun makeMove(row: Int, col: Int) {
        game.makeMove(row, col)

        if (game.finished) {
            if (game.isDraw)
                Toast.makeText(
                    this, getString(R.string.toast_draw_txt),
                    Toast.LENGTH_SHORT).show()
            else {
                Toast.makeText(
                    this,
                    game.winnerPlayer!!.name + " " +
                            getString(R.string.toast_win_txt), Toast.LENGTH_SHORT
                ).show()
                binding.player1Info.getFragment<PlayerFragment>()
                    .updatePlayerScoreTv()
                binding.player2Info.getFragment<PlayerFragment>()
                    .updatePlayerScoreTv()
            }
        }
    }

     private fun restart() {
        game.restart()

        for (i in 0 until game.field.rows)
            for (j in 0 until game.field.cols)
                buttons[i][j].setImageResource(0)
    }
}