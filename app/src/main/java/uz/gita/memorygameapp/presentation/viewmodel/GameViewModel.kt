package uz.gita.memorygameapp.presentation.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.data.model.SelectedFoundData

interface GameViewModel {
    val allGameDataLiveData: LiveData<List<GameData>>
    val twoSelectedFoundLiveData: LiveData<SelectedFoundData>

    fun loadData(level: LevelEnum)
    fun openClickImage(image: ImageView, context: Context)
    fun closeClickImage(image: ImageView, context: Context)
    fun itemClicked(image: ImageView, context: Context)
}