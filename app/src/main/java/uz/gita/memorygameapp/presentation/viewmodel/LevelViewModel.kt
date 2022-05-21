package uz.gita.memorygameapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.memorygameapp.data.model.LevelEnum

interface LevelViewModel {
    val openGameScreenLiveData: LiveData<LevelEnum>

    fun openGameScreen(level: LevelEnum)
}