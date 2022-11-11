package uz.gita.memorygameapp_slp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.memorygameapp_slp.data.model.LevelEnum

interface LevelViewModel {
    val openGameScreenLiveData: LiveData<LevelEnum>

    fun openGameScreen(level: LevelEnum)
}