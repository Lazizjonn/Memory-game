package uz.gita.memorygameapp_slp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp_slp.R
import uz.gita.memorygameapp_slp.data.model.LevelEnum
import uz.gita.memorygameapp_slp.databinding.ScreenLevelBinding
import uz.gita.memorygameapp_slp.presentation.viewmodel.LevelViewModel
import uz.gita.memorygameapp_slp.presentation.viewmodel.impl.LevelViewModelImpl

@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel: LevelViewModel by viewModels<LevelViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener { viewModel.openGameScreen(LevelEnum.EASY) }
        binding.medium.setOnClickListener { viewModel.openGameScreen(LevelEnum.MEDIUM) }
        binding.hard.setOnClickListener { viewModel.openGameScreen(LevelEnum.HARD) }

        viewModel.openGameScreenLiveData.observe(this@LevelScreen, openGameScreenObserver)
    }

    private val openGameScreenObserver = Observer<LevelEnum> {
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(it))
    }
}