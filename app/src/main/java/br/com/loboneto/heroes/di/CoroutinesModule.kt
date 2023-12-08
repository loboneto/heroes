package br.com.loboneto.heroes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    @DispatcherIO
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DispatcherMain
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherMain

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherIO
}
