package com.example.note


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var note : String,
)

