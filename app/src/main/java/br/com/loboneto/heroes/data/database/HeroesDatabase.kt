package br.com.loboneto.heroes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.loboneto.heroes.data.domain.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class HeroesDatabase : RoomDatabase() {

    abstract fun heroDao(): RoomHeroesDao

}
