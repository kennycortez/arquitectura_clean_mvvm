package com.example.data.mapper

abstract class Mapper<E,D> {

    abstract fun mapToEntity(type: D): E

    open fun mapToEntity(type: List<D>): List<E>{

        val list: ArrayList<E> = arrayListOf()

       type.forEach{
           list.add(mapToEntity(it))
       }

        return list

    }

}