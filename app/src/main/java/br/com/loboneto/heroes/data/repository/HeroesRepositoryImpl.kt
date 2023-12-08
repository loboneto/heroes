package br.com.loboneto.heroes.data.repository

import br.com.loboneto.heroes.di.CoroutinesModule
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.data.source.RoomDataSource
import br.com.loboneto.heroes.domain.repository.HeroesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(
    private val roomDataSource: RoomDataSource,
    @CoroutinesModule.DispatcherIO private val dispatcher: CoroutineDispatcher
): HeroesRepository {

    override suspend fun save(
        hero: HeroEntity
    ) = withContext(dispatcher) {
        roomDataSource.save(hero)
    }

    override suspend fun delete(
        hero: HeroEntity
    ) = withContext(dispatcher) {
        return@withContext roomDataSource.delete(hero)
    }

    override suspend fun fetch(): List<HeroEntity> = withContext(dispatcher) {
        return@withContext roomDataSource.fetch()
    }
}
