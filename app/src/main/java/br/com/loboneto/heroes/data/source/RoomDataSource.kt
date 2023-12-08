package br.com.loboneto.heroes.data.source

import br.com.loboneto.heroes.di.CoroutinesModule
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.data.database.HeroesDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RoomDataSource {
    suspend fun save(hero: HeroEntity)
    suspend fun delete(hero: HeroEntity)
    suspend fun fetch(): List<HeroEntity>
}

class RoomDataSourceImpl @Inject constructor(
    private val database: HeroesDatabase,
    @CoroutinesModule.DispatcherIO private val dispatcher: CoroutineDispatcher
) : RoomDataSource {
    override suspend fun save(hero: HeroEntity) = withContext(dispatcher) {
        database.heroDao().save(hero)
    }

    override suspend fun delete(hero: HeroEntity) = withContext(dispatcher) {
        database.heroDao().delete(hero)
    }
    override suspend fun fetch(): List<HeroEntity> = withContext(dispatcher) {
        return@withContext database.heroDao().get()
    }
}
