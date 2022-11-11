package uz.gita.memorygameapp_slp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.memorygameapp_slp.data.model.LevelEnum
import uz.gita.memorygameapp_slp.presentation.viewmodel.LevelViewModel
import javax.inject.Inject

@HiltViewModel
class LevelViewModelImpl @Inject constructor() : ViewModel(), LevelViewModel {
    override val openGameScreenLiveData = MutableLiveData<LevelEnum>()

    override fun openGameScreen(level: LevelEnum) {
        openGameScreenLiveData.value = level
    }
}