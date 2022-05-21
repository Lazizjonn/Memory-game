package uz.gita.memorygameapp.data.source.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("MEMORY", Context.MODE_PRIVATE)

    var attempt: Int
        get() = pref.getInt("ATTEMPT", 1)
        set(value) = pref.edit().putInt("ATTEMPT", value).apply()
}