package br.com.loboneto.heroes.di

import br.com.loboneto.heroes.domain.usecase.AddHeroUseCase
import br.com.loboneto.heroes.domain.usecase.DeleteHeroUseCase
import br.com.loboneto.heroes.domain.usecase.GetHeroesUseCase
import br.com.loboneto.heroes.ui.heroes.HeroesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Singleton
    @Provides
    fun providesHeroViewModel(
        addHeroUseCase: AddHeroUseCase,
        deleteHeroUseCase: DeleteHeroUseCase,
        getHeroesUseCase: GetHeroesUseCase
    ) = HeroesViewModel(addHeroUseCase, deleteHeroUseCase, getHeroesUseCase)
}
