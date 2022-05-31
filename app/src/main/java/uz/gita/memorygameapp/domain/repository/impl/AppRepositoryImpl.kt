package uz.gita.memorygameapp.domain.repository.impl

import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.data.source.local.sharedPref.SharedPref
import uz.gita.memorygameapp.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val pref: SharedPref
) : AppRepository {
    private val list = ArrayList<GameData>()
    init {
        load()
    }
    override suspend fun getDataByLevel(level: LevelEnum): List<GameData> {
        val count = level.x * level.y
        list.shuffle()
        return list.subList(0, count / 2)
    }
    override fun getEachLevel(level: LevelEnum): Int = pref.getLevel(level)
    override fun putEachLevel(level: LevelEnum, value: Int) = pref.putLevel(level, value)
    private fun load() {
        list.add(GameData(1, R.drawable.image_1))
        list.add(GameData(2, R.drawable.image_2))
        list.add(GameData(3, R.drawable.image_3))
        list.add(GameData(4, R.drawable.image_4))
        list.add(GameData(5, R.drawable.image_5))
        list.add(GameData(6, R.drawable.image_6))
        list.add(GameData(7, R.drawable.image_7))
        list.add(GameData(8, R.drawable.image_8))
        list.add(GameData(9, R.drawable.image_9))
        list.add(GameData(10, R.drawable.image_10))
        list.add(GameData(11, R.drawable.image_11))
        list.add(GameData(12, R.drawable.image_12))
        list.add(GameData(13, R.drawable.image_13))
        list.add(GameData(14, R.drawable.image_14))
        list.add(GameData(15, R.drawable.image_15))
        list.add(GameData(16, R.drawable.image_16))
        list.add(GameData(17, R.drawable.image_17))
        list.add(GameData(18, R.drawable.image_18))
        list.add(GameData(19, R.drawable.image_19))
        list.add(GameData(20, R.drawable.image_20))
        list.add(GameData(21, R.drawable.image_21))
        list.add(GameData(22, R.drawable.image_22))
        list.add(GameData(23, R.drawable.image_23))
        list.add(GameData(24, R.drawable.image_24))
        list.add(GameData(25, R.drawable.image_25))
        list.add(GameData(26, R.drawable.image_26))
        list.add(GameData(27, R.drawable.image_27))
        list.add(GameData(28, R.drawable.image_28))
        list.add(GameData(28, R.drawable.image_29))
        list.add(GameData(30, R.drawable.image_30))
        list.add(GameData(31, R.drawable.image_31))
        list.add(GameData(32, R.drawable.image_32))
        list.add(GameData(33, R.drawable.image_33))
        list.add(GameData(34, R.drawable.image_34))
        list.add(GameData(35, R.drawable.image_35))
        list.add(GameData(36, R.drawable.image_36))
        list.add(GameData(37, R.drawable.image_37))
        list.add(GameData(38, R.drawable.image_38))
        list.add(GameData(39, R.drawable.image_39))
        list.add(GameData(40, R.drawable.image_40))
        list.add(GameData(41, R.drawable.image_41))
        list.add(GameData(42, R.drawable.image_42))
        list.add(GameData(43, R.drawable.image_43))
        list.add(GameData(44, R.drawable.image_44))
        list.add(GameData(45, R.drawable.image_45))
        list.add(GameData(46, R.drawable.image_46))
        list.add(GameData(47, R.drawable.image_47))
        list.add(GameData(48, R.drawable.image_48))
        list.add(GameData(49, R.drawable.image_49))
        list.add(GameData(50, R.drawable.image_50))
        list.add(GameData(51, R.drawable.image_51))
        list.add(GameData(52, R.drawable.image_52))
        list.add(GameData(53, R.drawable.image_53))
    }


}