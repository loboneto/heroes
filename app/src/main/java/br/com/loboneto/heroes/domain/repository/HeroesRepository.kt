package br.com.loboneto.heroes.domain.repository

import br.com.loboneto.heroes.data.database.HeroEntity

interface HeroesRepository {
    suspend fun save(hero: HeroEntity)
    suspend fun delete(hero: HeroEntity)
    suspend fun fetch(): List<HeroEntity>
}
