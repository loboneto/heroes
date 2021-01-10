package br.com.loboneto.heroes.ui.heroes

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import br.com.loboneto.heroes.data.domain.Hero
import br.com.loboneto.heroes.data.local.HeroesRepository

class HeroesViewModel
@ViewModelInject
constructor(
    private val heroesRepository: HeroesRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun get() = liveData {
        emitSource(heroesRepository.fetch().asLiveData())
    }

    fun save(hero: Hero) = liveData {
        emitSource(heroesRepository.save(hero).asLiveData())
    }

    fun delete(hero: Hero) = liveData {
        emitSource(heroesRepository.delete(hero).asLiveData())
    }
}