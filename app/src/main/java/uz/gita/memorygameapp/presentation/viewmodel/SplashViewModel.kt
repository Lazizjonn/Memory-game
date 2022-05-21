package uz.gita.memorygameapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openNextScreen: LiveData<Unit>
}