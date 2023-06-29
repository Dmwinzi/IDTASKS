package com.example.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface Dao {

    @Query("SELECT * FROM Notes")
     fun getallnotes () : Flow<List<Entity>>

    @Insert
    suspend fun insertnotes (entity: Entity)

    @Update
    suspend fun updatenotes  (entity: Entity)

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun deletenotes (id: Int)



}