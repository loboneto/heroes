package br.com.loboneto.heroes.domain.usecase

import br.com.loboneto.heroes.di.CoroutinesModule
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.domain.RequestState
import br.com.loboneto.heroes.domain.repository.HeroesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteHeroUseCase {
    operator fun invoke(hero: HeroEntity): Flow<RequestState<Unit>>
}

class DeleteHeroUseCaseImpl @Inject constructor(
    private val repository: HeroesRepository,
    @CoroutinesModule.DispatcherIO private val dispatcher: CoroutineDispatcher
) : DeleteHeroUseCase {
    override fun invoke(hero: HeroEntity) = flow {
        emit(RequestState.Loading)
        try {
            repository.delete(hero)
            emit(RequestState.Success(Unit))
        } catch (e: Exception) {
            emit(RequestState.Failure(e))
        }
    }.flowOn(dispatcher)
}
