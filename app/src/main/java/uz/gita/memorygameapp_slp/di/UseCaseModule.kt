package uz.gita.memorygameapp_slp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygameapp_slp.domain.usecase.AllDataUseCase
import uz.gita.memorygameapp_slp.domain.usecase.impl.AllDataUseCaseImpl

@[Module InstallIn(SingletonComponent::class)]
interface UseCaseModule {
    @Binds
    fun getAllDataUseCase(impl: AllDataUseCaseImpl): AllDataUseCase
}