package com.example.to_do.Repository

import com.example.to_do.Data.Dao
import com.example.to_do.Data.Entity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class Repository @Inject constructor(var dao: Dao) {

    suspend fun getalltodos () : Flow<List<Entity>> {
        return dao.getalltodos()
    }

    suspend fun inserttodos (entity: Entity){
        dao.inserttodos(entity)
    }

    suspend fun deletetodos (id: Int){
        dao.deletetodo(id)
    }

    suspend fun  updatetodo (entity: Entity){
        dao.updatetodo(entity)
    }


}