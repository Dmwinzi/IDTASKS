package com.example.note

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.Entity

@Database(entities = [Entity::class],version = 1)
abstract class Database  : RoomDatabase(){

   abstract fun dao () : Dao

}