package com.example.to_do.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todos")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var description : String,
)

