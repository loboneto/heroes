package br.com.loboneto.heroes.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import br.com.loboneto.heroes.data.database.HeroEntity
import br.com.loboneto.heroes.domain.RequestState
import br.com.loboneto.heroes.domain.usecase.AddHeroUseCase
import br.com.loboneto.heroes.domain.usecase.DeleteHeroUseCase
import br.com.loboneto.heroes.domain.usecase.GetHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val addHeroUseCase: AddHeroUseCase,
    private val deleteHeroUseCase: DeleteHeroUseCase,
    private val getHeroesUseCase: GetHeroesUseCase,
) : ViewModel() {

    fun getHeroes() = liveData<RequestState<List<HeroEntity>>> {
        emitSource(getHeroesUseCase().asLiveData())
    }

    fun addHero(hero: HeroEntity) = liveData<RequestState<Unit>> {
        emitSource(addHeroUseCase(hero).asLiveData())
    }

    fun deleteHero(hero: HeroEntity) = liveData<RequestState<Unit>> {
        emitSource(deleteHeroUseCase(hero).asLiveData())
    }
}
