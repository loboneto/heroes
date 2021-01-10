package br.com.loboneto.heroes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.loboneto.heroes.data.domain.Hero

@Dao
interface RoomHeroesDao {
    @Insert(onConflict = REPLACE)
    fun save(hero: Hero)

    @Delete
    fun delete(hero: Hero)

    @Query("SELECT * FROM Hero")
    fun get(): List<Hero>
}
