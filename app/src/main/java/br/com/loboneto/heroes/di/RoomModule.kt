package br.com.loboneto.heroes.di

import android.content.Context
import androidx.room.Room
import br.com.loboneto.heroes.data.database.HeroesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context): HeroesDatabase {
        return Room
            .databaseBuilder(context, HeroesDatabase::class.java, "Heroes.db")
            .build()
    }
}
