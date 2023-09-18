package com.example.recipeappclone

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Recipe::class], exportSchema = false, version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getdao():Dao;


}