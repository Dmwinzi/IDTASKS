package com.example.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class Repository @Inject constructor(var dao: Dao) {

    suspend fun getallnotes () : Flow<List<Entity>> {
        return dao.getallnotes()
    }

    suspend fun insertnotes (entity: Entity){
        dao.insertnotes(entity)
    }

    suspend fun deletenotes (id: Int){
        dao.deletenotes(id)
    }

    suspend fun  updatenotes (entity: Entity){
        dao.updatenotes(entity)
    }


}