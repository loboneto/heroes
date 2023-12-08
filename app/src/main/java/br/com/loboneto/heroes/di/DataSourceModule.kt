package br.com.loboneto.heroes.di

import br.com.loboneto.heroes.data.source.RoomDataSource
import br.com.loboneto.heroes.data.source.RoomDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsRoomDataSource(
        impl: RoomDataSourceImpl
    ): RoomDataSource
}
