package com.example.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokedex(
    @PrimaryKey val uid:Int,

    //@ColumnInfo val abilities: List<String>?,

    @ColumnInfo val collectiblesSlug: String?,

    @ColumnInfo val detailPageURL: String?,

    @ColumnInfo val featured: String?,

    @ColumnInfo val height: Double?,

    @ColumnInfo val id: Int?,

    @ColumnInfo val name: String?,

    @ColumnInfo val number: String?,

    @ColumnInfo val slug: String?,

    @ColumnInfo val thumbnailAltText: String?,

    @ColumnInfo val thumbnailImage: String?,

    //@ColumnInfo val type: List<String>?,

    //@ColumnInfo val weakness: List<String>?,

    @ColumnInfo val weight: Double?
)