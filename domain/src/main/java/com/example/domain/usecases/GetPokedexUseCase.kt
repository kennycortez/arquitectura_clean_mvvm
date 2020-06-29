package com.example.domain.usecases

import com.example.domain.model.pokedex.PokedexItemModel
import com.example.domain.repositories.PokedexRepository
import com.example.helper.base.ResultType
import com.example.helper.base.UseCase
import com.example.helper.error.Failure
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(private val pokedexRepository: PokedexRepository): UseCase<List<PokedexItemModel>,Unit>() {

    override suspend fun run(params: Unit): ResultType<Failure, List<PokedexItemModel>> {
        return pokedexRepository.getPokedex()
    }
}