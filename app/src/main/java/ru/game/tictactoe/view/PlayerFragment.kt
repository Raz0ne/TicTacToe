package ru.game.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.game.tictactoe.Player
import ru.game.tictactoe.R
import ru.game.tictactoe.databinding.FragmentPlayerInfoBinding

class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerInfoBinding
    private lateinit var playerVM : Player

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate<FragmentPlayerInfoBinding?>(inflater,
            R.layout.fragment_player_info, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                player = playerVM
            }

        return binding.root
    }

    fun setPlayerVm(player: Player) { playerVM = player }
}