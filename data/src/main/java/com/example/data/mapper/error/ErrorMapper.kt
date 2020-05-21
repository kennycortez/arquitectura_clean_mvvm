package com.example.data.mapper.error

import com.example.data.entities.exception.ErrorEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.exception.ErrorModel

class ErrorMapper:Mapper<ErrorModel, ErrorEntity>() {

    override fun mapToEntity(type: ErrorEntity): ErrorModel {
        return ErrorModel(
            type.code,
            type.description,
            type.errorProperties.title,
            type.statusCode
        )


    }
}