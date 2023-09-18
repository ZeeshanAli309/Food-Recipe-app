package com.example.recipeappclone

import androidx.room.Dao
import androidx.room.Query
@Dao
interface Dao {
    @Query("Select * from recipe")
    fun getAll():List<Recipe?>
}