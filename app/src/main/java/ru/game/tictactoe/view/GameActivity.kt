package ru.game.tictactoe.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.game.tictactoe.Game
import ru.game.tictactoe.Player
import ru.game.tictactoe.R
import ru.game.tictactoe.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var intent: Intent

    private lateinit var game: Game
    private lateinit var buttons: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        intent = getIntent()

        game = Game(arrayOf(Player(intent.getStringExtra("firstPlayerName")!!),
            Player(intent.getStringExtra("secondPlayerName")!!)))

        binding.backBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.alert_dialog_title)
            val alertDialog = builder.create()
            alertDialog.show()
        }

        binding.restartGameBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.alert_dialog_title)
            val alertDialog = builder.create()
            alertDialog.show()
        }

        setContentView(binding.root)
    }

    private fun restart() {
        game.restart()

        val field = game.getField()
        for (i in 0..field.size)
            for (j in 0..field[i].size) {
                if (field[i][j].getPlayer() == null)
                    buttons[i][j].text = ""
                else
                    buttons[i][j].text = field[i][j].getPlayer()!!.getName()
            }
    }
}