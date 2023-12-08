package br.com.loboneto.heroes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface RoomHeroesDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(hero: HeroEntity)

    @Delete
    suspend fun delete(hero: HeroEntity)

    @Query("SELECT * FROM HeroEntity")
    suspend fun get(): List<HeroEntity>
}
