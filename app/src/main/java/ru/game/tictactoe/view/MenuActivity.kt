package ru.game.tictactoe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.game.tictactoe.R
import ru.game.tictactoe.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Menu_Activity", "Activity is creating...")
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        intent = Intent(this, GameActivity::class.java)

        binding.startGameBtn.setOnClickListener {
            startGame()
        }

        setContentView(binding.root)
        Log.d("Menu_Activity", "Activity is created")
    }

    private fun startGame() {
        intent.putExtra("player1Name", binding.player1NameEt.text.toString())
        intent.putExtra("player2Name", binding.player2NameEt.text.toString())
        startActivity(intent)
    }
}