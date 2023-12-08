package br.com.loboneto.heroes.di

import br.com.loboneto.heroes.domain.usecase.AddHeroUseCase
import br.com.loboneto.heroes.domain.usecase.AddHeroUseCaseImpl
import br.com.loboneto.heroes.domain.usecase.DeleteHeroUseCase
import br.com.loboneto.heroes.domain.usecase.DeleteHeroUseCaseImpl
import br.com.loboneto.heroes.domain.usecase.GetHeroesUseCase
import br.com.loboneto.heroes.domain.usecase.GetHeroesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindsAddHeroUseCase(
        impl: AddHeroUseCaseImpl
    ): AddHeroUseCase

    @Singleton
    @Binds
    abstract fun bindsDeleteHeroUseCase(
        impl: DeleteHeroUseCaseImpl
    ): DeleteHeroUseCase

    @Singleton
    @Binds
    abstract fun bindsGetHeroesUseCase(
        impl: GetHeroesUseCaseImpl
    ): GetHeroesUseCase
}
