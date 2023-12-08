package br.com.loboneto.heroes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeroEntity::class], version = 1, exportSchema = false)
abstract class HeroesDatabase : RoomDatabase() {
    abstract fun heroDao(): RoomHeroesDao
}
