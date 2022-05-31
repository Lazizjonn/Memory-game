package uz.gita.memorygameapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum

interface AllDataUseCase {
    fun getDataByLevel(level: LevelEnum): Flow<List<GameData>>
    fun getEachLevel(level: LevelEnum): Int
    fun putEachLevel(level: LevelEnum, value: Int)
}