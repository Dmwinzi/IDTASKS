package com.example.to_do.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version = 4)
abstract class Database  : RoomDatabase(){

   abstract fun dao () : Dao

}