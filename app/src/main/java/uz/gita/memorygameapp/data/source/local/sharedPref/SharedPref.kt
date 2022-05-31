package uz.gita.memorygameapp.data.source.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.memorygameapp.data.model.LevelEnum
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("MEMORY", Context.MODE_PRIVATE)

    fun putLevel(level: LevelEnum, value: Int){
        when(level) {
            LevelEnum.EASY -> {
                pref.edit().putInt("LEVEL_EASY", value).apply()
            }
            LevelEnum.MEDIUM -> {
                pref.edit().putInt("LEVEL_MEDIUM", value).apply()
            }
            LevelEnum.HARD -> {
                pref.edit().putInt("LEVEL_HARD", value).apply()
            }
        }
    }
    fun getLevel(level: LevelEnum): Int{
        return when(level) {
            LevelEnum.EASY -> {
                pref.getInt("LEVEL_EASY", 1)
            }
            LevelEnum.MEDIUM -> {
                pref.getInt("LEVEL_MEDIUM", 1)
            }
            LevelEnum.HARD -> {
                pref.getInt("LEVEL_HARD", 1)
            }
        }
    }
}