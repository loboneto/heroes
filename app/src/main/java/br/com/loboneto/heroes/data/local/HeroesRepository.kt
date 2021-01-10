package br.com.loboneto.heroes.data.local

import br.com.loboneto.heroes.data.dao.RoomState
import br.com.loboneto.heroes.data.domain.Hero
import br.com.loboneto.heroes.data.source.RoomDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HeroesRepository(private val roomDataSource: RoomDataSource) {

    suspend fun save(favorite: Hero): Flow<RoomState<Unit>> = withContext(Dispatchers.Main) {
        return@withContext roomDataSource.save(favorite)
    }

    suspend fun delete(favorite: Hero): Flow<RoomState<Unit>> = withContext(Dispatchers.Main) {
        return@withContext roomDataSource.delete(favorite)
    }

    suspend fun fetch(): Flow<RoomState<List<Hero>>> = withContext(Dispatchers.Main) {
        return@withContext roomDataSource.fetch()
    }
}