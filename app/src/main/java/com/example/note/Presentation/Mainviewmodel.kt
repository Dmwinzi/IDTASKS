package com.example.note


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Mainviewmodel @Inject constructor(val repo : Repository) : ViewModel () {

   private val notes  = MutableStateFlow<List<Entity>>(listOf())
   var _allnotes = notes.asStateFlow()


    fun getallnotes (){
        viewModelScope.launch {
            repo.getallnotes().collect{
                notes.value  = it
            }
        }
    }

    suspend fun insertnotes (entity: Entity){
        repo.insertnotes(entity)
    }


    suspend fun deletetodo (id: Int){
        repo.deletenotes(id)
    }

    suspend fun updatenotes (entity: Entity){
        repo.updatenotes(entity)
    }




}