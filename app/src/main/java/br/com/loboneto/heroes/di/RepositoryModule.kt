package br.com.loboneto.heroes.di

import br.com.loboneto.heroes.data.repository.HeroesRepositoryImpl
import br.com.loboneto.heroes.domain.repository.HeroesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsHeroesRepository(
        impl: HeroesRepositoryImpl
    ): HeroesRepository
}
