package com.example.to_do.Presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Todo(){

    var viewModel : Mainviewmodel  = hiltViewModel()
    var context  = LocalContext.current
    var showaddtaskdialog by remember { mutableStateOf(false)}
    var showupdatetaskdialog by remember { mutableStateOf(false)}
    viewModel.getalltodos()
    var todos  = viewModel._alltodos.collectAsState()
   var scope  = rememberCoroutineScope()
    var id by remember { mutableStateOf(0)}
    var title  by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}

    Addtaskdialog(context = context,viewModel = viewModel,dismiss = showaddtaskdialog, onDismiss = {showaddtaskdialog = false})
    Updatedialog(context = context, viewModel = viewModel , dismiss = showupdatetaskdialog, onDismiss = {showupdatetaskdialog  = false},id, description, title)

    Scaffold(modifier = Modifier.fillMaxSize(),
          floatingActionButton = { FloatingActionButton(onClick = { showaddtaskdialog = true}){
              Icon(imageVector = Icons.Default.Add, contentDescription = null)
          }}, floatingActionButtonPosition = FabPosition.End
        ) {
        LazyColumn(modifier = Modifier.padding(it)){
            items(todos.value){todo ->
                Card(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        id = todo.id
                        title  = todo.title
                        description  = todo.description
                        showupdatetaskdialog = true
                    }
                    .padding(5.dp)) {
                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(modifier = Modifier.padding(start = 20.dp, end = 25.dp)) {
                            Text(text = "Title : ${todo.title}")
                            Text(text = todo.description)
                        }
                        Column() {
                            IconButton(onClick = {
                                  scope.launch {
                                       try {
                                           viewModel.deletetodo(todo.id)
                                           Toast.makeText(context,"Task deleted succesfully",Toast.LENGTH_LONG).show()
                                       } catch (e : Exception){
                                           Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
                                       }
                                  }
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                            }
                        }

                    }
                }
            }
        }
    }

}