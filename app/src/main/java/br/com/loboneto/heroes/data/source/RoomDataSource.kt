package br.com.loboneto.heroes.data.source

import br.com.loboneto.heroes.data.dao.RoomState
import br.com.loboneto.heroes.data.database.HeroesDatabase
import br.com.loboneto.heroes.data.domain.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RoomDataSource(private val database: HeroesDatabase) {

    fun save(hero: Hero) = flow {
        emit(RoomState.Loading)
        try {
            database.heroDao().save(hero)
            emit(RoomState.Success(Unit))
        } catch (e: Exception) {
            emit(RoomState.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun delete(hero: Hero) = flow {
        emit(RoomState.Loading)
        try {
            database.heroDao().delete(hero)
            emit(RoomState.Success(Unit))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RoomState.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun fetch() = flow {
        emit(RoomState.Loading)
        try {
            val heroes: List<Hero> = database.heroDao().get()
            emit(RoomState.Success(heroes))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RoomState.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
