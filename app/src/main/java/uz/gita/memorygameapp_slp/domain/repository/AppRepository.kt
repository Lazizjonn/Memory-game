package uz.gita.memorygameapp_slp.domain.repository

import uz.gita.memorygameapp_slp.data.model.GameData
import uz.gita.memorygameapp_slp.data.model.LevelEnum

interface AppRepository {
    suspend fun getDataByLevel(level: LevelEnum): List<GameData>
    fun getEachLevel(level: LevelEnum): Int
    fun putEachLevel(level: LevelEnum, value: Int)
}