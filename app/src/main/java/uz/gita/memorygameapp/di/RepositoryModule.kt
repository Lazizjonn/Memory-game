package uz.gita.memorygameapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygameapp.domain.repository.AppRepository
import uz.gita.memorygameapp.domain.repository.impl.AppRepositoryImpl

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @Binds
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

}