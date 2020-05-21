package com.example.data.mapper.pokemon

import com.example.data.entities.PokemonEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.NextEvolutionModel
import com.example.domain.model.PokemonModel
import com.example.domain.model.PrevEvolutionModel

class PokemonMapper:Mapper<PokemonModel,PokemonEntity>() {

    var nextEvolutionMapper = NextEvolutionMapper()
    var prevEvolutionMapper = PrevEvolutionMapper()

    override fun mapToEntity(type: List<PokemonEntity>): List<PokemonModel> {
        val itemList:MutableList<PokemonModel> = mutableListOf()

        type.forEach {

            var itemNextEvolution:List<NextEvolutionModel> = arrayListOf()
            if(it.nextEvolution!=null){
                itemNextEvolution =nextEvolutionMapper.mapToEntity(it.nextEvolution!!)
            }
         /*   it.nextEvolution.let {
               _ito ->

            }*/

            var itemPrevEvolution:List<PrevEvolutionModel> = arrayListOf()
            if (it.prevEvolution!=null){
                 itemPrevEvolution=prevEvolutionMapper.mapToEntity(it.prevEvolution!!)
            }

           /* it.prevEvolution.let {
                _ita -> itemPrevEvolution =prevEvolutionMapper.mapToEntity(it.prevEvolution!!)
            }*/


            val item= PokemonModel(it.avgSpawns,it.candy,it.candyCount,it.egg,it.height,it.id,it.img,it.multipliers,it.name,
                itemNextEvolution,it.num,itemPrevEvolution,it.spawnChance,it.spawnTime,it.type,it.weaknesses,it.weight)
            itemList.add(item)
        }

        return itemList
    }

    override fun mapToEntity(type: PokemonEntity): PokemonModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}