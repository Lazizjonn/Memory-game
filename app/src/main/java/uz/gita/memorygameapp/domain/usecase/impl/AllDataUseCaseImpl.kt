package uz.gita.memorygameapp.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.memorygameapp.data.model.GameData
import uz.gita.memorygameapp.data.model.LevelEnum
import uz.gita.memorygameapp.domain.repository.AppRepository
import uz.gita.memorygameapp.domain.usecase.AllDataUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllDataUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : AllDataUseCase {
    override fun getDataByLevel(level: LevelEnum): Flow<List<GameData>> = flow {
        val result = ArrayList<GameData>()
        val demo = repository.getDataByLevel(level)

        result.addAll(demo)
        result.addAll(demo)

        result.shuffle()

        emit(result)
    }.flowOn(Dispatchers.IO)
    override fun getEachLevel(level: LevelEnum): Int = repository.getEachLevel(level)
    override fun putEachLevel(level: LevelEnum, value: Int) = repository.putEachLevel(level, value)
}