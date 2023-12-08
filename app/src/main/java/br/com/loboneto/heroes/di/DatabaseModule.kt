package br.com.loboneto.heroes.di

import android.content.Context
import androidx.room.Room
import br.com.loboneto.heroes.data.database.HeroesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HeroesDatabase =
        Room.databaseBuilder(context, HeroesDatabase::class.java, "Heroes.db").build()
}
