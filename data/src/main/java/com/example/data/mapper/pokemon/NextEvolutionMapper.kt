package com.example.data.mapper.pokemon

import com.example.data.entities.NextEvolutionEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.NextEvolutionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NextEvolutionMapper @Inject constructor():Mapper<NextEvolutionModel,NextEvolutionEntity>() {


    override fun mapToEntity(type: List<NextEvolutionEntity>): List<NextEvolutionModel> {
        val itemList:MutableList<NextEvolutionModel> = mutableListOf()

        type.forEach {
            val nextEvolutionModel = NextEvolutionModel(it.name,it.num)
            itemList.add(nextEvolutionModel)
        }
        return itemList
    }

    override fun mapToEntity(type: NextEvolutionEntity): NextEvolutionModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}