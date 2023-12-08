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

interface GetHeroesUseCase {
    operator fun invoke(): Flow<RequestState<List<HeroEntity>>>
}

class GetHeroesUseCaseImpl @Inject constructor(
    private val repository: HeroesRepository,
    @CoroutinesModule.DispatcherIO private val dispatcher: CoroutineDispatcher
) : GetHeroesUseCase {
    override fun invoke() = flow {
        emit(RequestState.Loading)
        try {
            val heroes: List<HeroEntity> = repository.fetch()
            emit(RequestState.Success(heroes))
        } catch (e: Exception) {
            emit(RequestState.Failure(e))
        }
    }.flowOn(dispatcher)
}
