package uz.gita.memorygameapp.presentation.viewmodel.impl

import android.content.Context
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.data.model.SelectedFoundData
import uz.gita.memorygameapp.domain.usecase.AllDataUseCase
import uz.gita.memorygameapp.presentation.viewmodel.GameViewModel
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

    override fun loadData(level: LevelEnum) {
        useCase.getDataByLevel(level).onEach {
            allGameDataLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun openClickImage(image: ImageView, context: Context) {
        image.animate().setDuration(200).rotationY(90f).withEndAction {
            image.setImageResource((image.tag as GameData).image)
            image.animate().setDuration(200).rotationY(180f).setInterpolator(DecelerateInterpolator()).withEndAction {
//                Toast.makeText(context, "end", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun closeClickImage(image: ImageView, context: Context) {
        image.animate().setDuration(200).rotationY(90f).withEndAction {
            image.setImageResource(R.drawable.image_back)
            image.animate().setDuration(200).rotationY(0f).setInterpolator(DecelerateInterpolator()).withEndAction {
//                Toast.makeText(context, "end", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun itemClicked(image: ImageView, context: Context) {


        if (image.rotationY < 1f && count < 3 ) {
            viewModelScope.launch {
                if (count == 0) {
                    count = 1
                    image1 = image
                    openClickImage(image, context)
                } else if (image != image1 && count == 1 ) {
                    openClickImage(image, context)
                    count = 2
                    image2 = image
                    delay(1000)
                    checkTwoSelected(image1!!, image2!!)
                } else {
                    // 3rd image clicked
                }
            }
        }
    }

    private fun checkTwoSelected(firstImage: ImageView, secondImage: ImageView) {
       if  ((firstImage.tag as GameData).id == (secondImage.tag as GameData).id) {
           twoSelectedFoundLiveData.value = SelectedFoundData(true, firstImage, secondImage)
           count = 0
       } else {
           twoSelectedFoundLiveData.value = SelectedFoundData(false, firstImage, secondImage)
           count = 0
       }
    }

}