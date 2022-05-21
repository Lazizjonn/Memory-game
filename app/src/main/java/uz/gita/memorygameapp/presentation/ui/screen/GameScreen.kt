package uz.gita.memorygameapp.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.data.model.SelectedFoundData
import uz.gita.memorygameapp.databinding.ScreenGameBinding
import uz.gita.memorygameapp.presentation.viewmodel.GameViewModel
import uz.gita.memorygameapp.presentation.viewmodel.impl.GameViewModelImpl
import uz.gita.memorygameapp.utils.gone
import uz.gita.memorygameapp.utils.scope

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    private val args: GameScreenArgs by navArgs()
    private var count = 0
    private var level: LevelEnum = LevelEnum.EASY
    private var _height = 0
    private var _width = 0
    private val views = ArrayList<ImageView>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        args.level.apply {
            level = this
            count = x * y
        }
        main.post {
            _height = main.height / (level.y + 2)
            _width = main.width / (level.x + 1)
            container.layoutParams.apply {
                height = level.y * _height
                width = level.x * _width
            }
            loadView()
            viewModel.loadData(level)
        }

        viewModel.allGameDataLiveData.observe(viewLifecycleOwner, allGameDataObserver)
        viewModel.twoSelectedFoundLiveData.observe(viewLifecycleOwner, twoSelectedFoundObserver)
    }

    private val allGameDataObserver = Observer<List<GameData>> { list ->
        for (i in list.indices) {
            views[i].apply {
                tag = list[i]
                setOnClickListener {
                    viewModel.itemClicked(it as ImageView, requireContext())
                }
            }
        }
    }

    private val twoSelectedFoundObserver = Observer<SelectedFoundData> {
        if (it.bool) {
            it.image1.gone()
            views.remove(it.image1)
            it.image2.gone()
            views.remove(it.image2)
        } else {
            viewModel.closeClickImage(it.image1, requireContext())
            viewModel.closeClickImage(it.image2, requireContext())
        }
        if (views.size == 0){
            findNavController().navigateUp()
        }
    }

    private fun loadView() {
        for (i in 0 until level.x) {
            for (j in 0 until level.y) {
                val image = ImageView(requireContext())
                binding.container.addView(image)
                val lp = image.layoutParams as RelativeLayout.LayoutParams
                lp.apply {
                    height = _height
                    width = _width
                }
                image.x = i * _width.toFloat()
                image.y = j * _height * 1f
                image.scaleType = ImageView.ScaleType.FIT_XY
                lp.setMargins(4, 4, 4, 4)
                image.layoutParams = lp
                image.setImageResource(R.drawable.image_back)
                views.add(image)
            }
        }
    }

}