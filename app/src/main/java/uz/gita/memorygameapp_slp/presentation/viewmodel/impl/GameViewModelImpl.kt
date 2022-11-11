package uz.gita.memorygameapp_slp.presentation.viewmodel.impl

import android.content.Context
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.memorygameapp_slp.R
import uz.gita.memorygameapp_slp.data.model.GameData
import uz.gita.memorygameapp_slp.data.model.LevelEnum
import uz.gita.memorygameapp_slp.data.model.SelectedFoundData
import uz.gita.memorygameapp_slp.domain.usecase.AllDataUseCase
import uz.gita.memorygameapp_slp.presentation.viewmodel.GameViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModelImpl @Inject constructor(
    private val useCase: AllDataUseCase
) : ViewModel(), GameViewModel {
    private var count = 0
    private var image1: ImageView? = null
    private var image2: ImageView? = null
    override val allGameDataLiveData = MutableLiveData<List<GameData>>()
    override val twoSelectedFoundLiveData = MutableLiveData<SelectedFoundData>()
    override val getAttemptLiveData = MutableLiveData<Int>()
    override val getLevelLiveData = MutableLiveData<Int>()
    override val goToHomeLiveData = MutableLiveData<Unit>()
    override val openWinnerDialog = MutableLiveData<Unit>()

    override fun loadData(level: LevelEnum) {
        useCase.getDataByLevel(level).onEach {
            allGameDataLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun openClickImage(image: ImageView, context: Context) {
        image.animate().setDuration(200).rotationY(90f).withEndAction {
            image.setImageResource((image.tag as GameData).image)
            image.animate().setDuration(200).rotationY(180f).setInterpolator(DecelerateInterpolator()).withEndAction {
            }
        }.start()
    }

    override fun closeClickImage(image: ImageView, context: Context) {
        image.animate().setDuration(200).rotationY(90f).withEndAction {
            image.setImageResource(R.drawable.image_back)
            image.animate().setDuration(200).rotationY(0f).setInterpolator(DecelerateInterpolator()).withEndAction {
            }
        }.start()
    }

    override fun itemClicked(image: ImageView, context: Context, level: LevelEnum, attempt: Int) {
        if (image.rotationY < 1f && count < 3) {
            viewModelScope.launch {
                if (count == 0) {
                    count = 1
                    image1 = image
                    openClickImage(image, context)
                } else if (image != image1 && count == 1) {
                    openClickImage(image, context)
                    count = 2
                    image2 = image
                    getAttemptLiveData.value = attempt + 1
                    delay(1000)
                    checkTwoSelected(image1!!, image2!!)
                }
            }
        }
    }

    override fun getEachLevel(level: LevelEnum) {
        getAttemptLiveData.value = 0
        val levelOfEach = useCase.getEachLevel(level)
        if (levelOfEach < 16) {
            getLevelLiveData.value = levelOfEach
        } else {
            useCase.putEachLevel(level, 1)
            getLevelLiveData.value = 1
            openWinnerDialog.value = Unit
        }

    }

    override fun putEachLevel(level: LevelEnum, value: Int) = useCase.putEachLevel(level, value)

    override fun goToHome() {
        goToHomeLiveData.value = Unit
    }

    private fun checkTwoSelected(firstImage: ImageView, secondImage: ImageView) {
        if ((firstImage.tag as GameData).id == (secondImage.tag as GameData).id) {
            twoSelectedFoundLiveData.value = SelectedFoundData(true, firstImage, secondImage)
            count = 0
        } else {
            twoSelectedFoundLiveData.value = SelectedFoundData(false, firstImage, secondImage)
            count = 0
        }
    }

    override fun onCleared() {
        super.onCleared()
        image1 = null
        image2 = null
    }
}