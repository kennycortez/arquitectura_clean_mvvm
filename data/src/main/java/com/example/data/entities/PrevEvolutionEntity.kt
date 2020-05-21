package com.example.data.entities


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PrevEvolutionEntity(
    @SerializedName("name") var name: String?,
    @SerializedName("num") var num: String?
): Serializable