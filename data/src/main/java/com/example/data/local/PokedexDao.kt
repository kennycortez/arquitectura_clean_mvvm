package com.example.data.local

import androidx.room.*
import javax.inject.Inject

@Dao
interface PokedexDao {

    @Query("Select * from Pokedex /*p group by p.number having count(*) > 1*/")
    fun getAll():List<Pokedex>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokedex(pokedex:Pokedex)
}