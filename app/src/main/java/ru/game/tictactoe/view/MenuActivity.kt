package ru.game.tictactoe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.game.tictactoe.R
import ru.game.tictactoe.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        intent = Intent(this, GameActivity::class.java)

        binding.startGameBtn.setOnClickListener { startGame() }

        setContentView(R.layout.activity_menu)
    }

    private fun startGame() {
        intent.putExtra("firstPlayerName", binding.firstPlayerNameEt.text ?: "X")
        intent.putExtra("secondPlayerName", binding.secondPlayerNameEt.text ?: "O")
        startActivity(intent)
    }
}