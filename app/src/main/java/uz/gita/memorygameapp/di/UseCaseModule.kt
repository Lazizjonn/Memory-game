package uz.gita.memorygameapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygameapp.domain.usecase.AllDataUseCase
import uz.gita.memorygameapp.domain.usecase.impl.AllDataUseCaseImpl

@[Module InstallIn(SingletonComponent::class)]
interface UseCaseModule {

    @Binds
    fun getAllDataUseCase(impl: AllDataUseCaseImpl): AllDataUseCase

}