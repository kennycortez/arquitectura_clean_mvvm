package com.example.data.mapper.pokemon

import com.example.data.entities.PrevEvolutionEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.PrevEvolutionModel

class PrevEvolutionMapper:Mapper<PrevEvolutionModel,PrevEvolutionEntity>() {

   /* override fun mapToEntity(type: PrevEvolutionEntity): PrevEvolutionModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/


    override fun mapToEntity(type: List<PrevEvolutionEntity>): List<PrevEvolutionModel> {
        val itemList:MutableList<PrevEvolutionModel> = mutableListOf()

        type?.forEach {
            val nextEvolutionModel = PrevEvolutionModel(it?.name,it?.num)
            itemList.add(nextEvolutionModel)
        }
        return itemList
    }

    override fun mapToEntity(type: PrevEvolutionEntity): PrevEvolutionModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}