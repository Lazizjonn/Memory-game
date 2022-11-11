package uz.gita.memorygameapp_slp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.balysv.materialripple.MaterialRippleLayout
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp_slp.R
import uz.gita.memorygameapp_slp.data.model.GameData
import uz.gita.memorygameapp_slp.data.model.LevelEnum
import uz.gita.memorygameapp_slp.data.model.SelectedFoundData
import uz.gita.memorygameapp_slp.databinding.ScreenGameBinding
import uz.gita.memorygameapp_slp.presentation.viewmodel.GameViewModel
import uz.gita.memorygameapp_slp.presentation.viewmodel.impl.GameViewModelImpl
import uz.gita.memorygameapp_slp.utils.gone

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val binding by viewBinding(ScreenGameBinding::bind)
    private val viewModel: GameViewModel by viewModels<GameViewModelImpl>()
    private val args: GameScreenArgs by navArgs()
    private var level: LevelEnum = LevelEnum.EASY
    private var count = 0
    private var stage = 1
    private var attempt = 0
    private var _height = 0
    private var _width = 0
    private val views = ArrayList<ImageView>()
    private lateinit var myView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.level.apply {
            level = this
            count = x * y
        }
        findEachSize()
        clicks()
        viewModel()
    }

    private fun findEachSize() {
        binding.main.post {
            _height = binding.main.height / (level.y + 3)
            _width = binding.main.width / (level.x + 1)
            binding.container.layoutParams.apply {
                height = level.y * _height
                width = level.x * _width
            }
            loadView()
            viewModel.loadData(level)
        }
    }

    private fun clicks() {
        binding.home.setOnClickListener { viewModel.goToHome() }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun viewModel() {
        viewModel.allGameDataLiveData.observe(viewLifecycleOwner, allGameDataObserver)
        viewModel.twoSelectedFoundLiveData.observe(viewLifecycleOwner, twoSelectedFoundObserver)
        viewModel.getAttemptLiveData.observe(viewLifecycleOwner, getAttemptObserver)
        viewModel.getLevelLiveData.observe(viewLifecycleOwner, getLevelObserver)
        viewModel.goToHomeLiveData.observe(this@GameScreen, goToHomeObserver)
        viewModel.openWinnerDialog.observe(viewLifecycleOwner, openWinnerObserver)
    }

    private fun loadView() {
        viewModel.getEachLevel(level)
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

    private val openWinnerObserver = Observer<Unit> {
        myView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_achievement_congrat, null, false)
        val dialog = AlertDialog.Builder(requireContext(),R.style.WrapContentDialog)
            .setCancelable(false)
            .setView(myView)
            .create()
        val next = myView.findViewById<MaterialRippleLayout>(R.id.bt_action)
        next.setOnClickListener {
            dialog.dismiss()
            findNavController().navigateUp()
        }
        dialog.show()
    }
    private val goToHomeObserver = Observer<Unit> { findNavController().navigateUp() }
    private val allGameDataObserver = Observer<List<GameData>> { list ->
        for (i in list.indices) {
            views[i].apply {
                tag = list[i]
                setOnClickListener {
                    viewModel.itemClicked(it as ImageView, requireContext(), level, attempt)
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
        if (views.size == 0) {
            viewModel.putEachLevel(level, stage + 1)
            viewModel.getEachLevel(level)
            loadView()
            viewModel.loadData(level)
        }
    }
    private val getAttemptObserver = Observer<Int> {
        binding.attempt.text = it.toString()
        attempt = it
    }
    private val getLevelObserver = Observer<Int> {
        binding.step.text = it.toString()
        binding.levelText.text = "$it/15"
        stage = it
    }
}