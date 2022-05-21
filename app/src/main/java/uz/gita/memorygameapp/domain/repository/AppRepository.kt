package uz.gita.memorygameapp.domain.repository

import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum

interface AppRepository {

   suspend fun getDataByLevel(level: LevelEnum): List<GameData>

}