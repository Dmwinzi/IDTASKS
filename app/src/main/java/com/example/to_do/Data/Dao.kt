package com.example.to_do.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface Dao {

    @Query("SELECT * FROM Todos")
     fun getalltodos () : Flow<List<Entity>>

    @Insert
    suspend fun inserttodos (entity: Entity)

    @Update
    suspend fun updatetodo  (entity: Entity)

    @Query("DELETE FROM Todos WHERE id = :id")
    suspend fun deletetodo (id: Int)



}