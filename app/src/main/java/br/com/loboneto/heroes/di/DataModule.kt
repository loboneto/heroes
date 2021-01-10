package br.com.loboneto.heroes.di

import br.com.loboneto.heroes.data.database.HeroesDatabase
import br.com.loboneto.heroes.data.local.HeroesRepository
import br.com.loboneto.heroes.data.source.RoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRoomDataSource(database: HeroesDatabase): RoomDataSource =
        RoomDataSource(database)

    @Singleton
    @Provides
    fun provideHeroesRepository(dataSource: RoomDataSource): HeroesRepository =
        HeroesRepository(dataSource)
}
