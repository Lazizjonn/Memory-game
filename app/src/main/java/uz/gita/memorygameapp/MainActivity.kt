package uz.gita.memorygameapp

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.data.source.local.sharedPref.SharedPref
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)