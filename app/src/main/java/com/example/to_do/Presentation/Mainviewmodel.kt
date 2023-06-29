package com.example.to_do.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.Data.Entity
import com.example.to_do.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Mainviewmodel @Inject constructor(val repo : Repository) : ViewModel () {

   private val alltodos  = MutableStateFlow<List<Entity>>(listOf())
   var _alltodos = alltodos.asStateFlow()


    fun getalltodos (){
        viewModelScope.launch {
            repo.getalltodos().collect{
                alltodos.value  = it
            }
        }
    }

    suspend fun inserttodo (entity: Entity){
        repo.inserttodos(entity)
    }


    suspend fun deletetodo (id: Int){
        repo.deletetodos(id)
    }

    suspend fun updatetodo (entity: Entity){
        repo.updatetodo(entity)
    }




}