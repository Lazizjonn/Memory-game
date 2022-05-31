package uz.gita.memorygameapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.databinding.ScreenSplashBinding
import uz.gita.memorygameapp.presentation.viewmodel.SplashViewModel
import uz.gita.memorygameapp.presentation.viewmodel.impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreen.observe(viewLifecycleOwner, openNextObserver)
    }
    private val openNextObserver = Observer<Unit> {
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToLevelScreen())
    }
}