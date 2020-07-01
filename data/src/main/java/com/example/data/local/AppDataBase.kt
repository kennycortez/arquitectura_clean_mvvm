package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(entities = [Pokedex::class], version = 1)
abstract class AppDataBase  @Inject constructor(): RoomDatabase() {
    abstract fun pokedexDao():PokedexDao
}