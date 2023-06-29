package com.example.note

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.Mainviewmodel
import com.example.note.Updatedialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notes(){

    var viewModel : Mainviewmodel = hiltViewModel()
    var context  = LocalContext.current
    var showaddtaskdialog by remember { mutableStateOf(false)}
    var showupdatetaskdialog by remember { mutableStateOf(false)}
    viewModel.getallnotes()
    var notes  = viewModel._allnotes.collectAsState()
   var scope  = rememberCoroutineScope()
    var id by remember { mutableStateOf(0)}
    var title  by remember { mutableStateOf("")}
    var not by remember { mutableStateOf("")}

    Addnotedialog(context = context,viewModel = viewModel,dismiss = showaddtaskdialog, onDismiss = {showaddtaskdialog = false})
    Updatedialog(context = context, viewModel = viewModel , dismiss = showupdatetaskdialog, onDismiss = {showupdatetaskdialog  = false},id,not, title)

    Scaffold(modifier = Modifier.fillMaxSize(),
          floatingActionButton = { FloatingActionButton(onClick = { showaddtaskdialog = true}){
              Icon(imageVector = Icons.Default.Add, contentDescription = null)
          }}, floatingActionButtonPosition = FabPosition.End
        ) {
        LazyColumn(modifier = Modifier.padding(it)){
            items(notes.value){note ->
                Card(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        id = note.id
                        title = note.title
                        not = note.note
                        showupdatetaskdialog = true
                    }
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    .padding(5.dp)) {
                    var expand  by remember { mutableStateOf(false)}
                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(modifier = Modifier.padding(start = 20.dp, end = 25.dp)) {
                           Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                               Text(text = "Title : ${note.title}")
                              Row(verticalAlignment = Alignment.CenterVertically) {
                                  IconToggleButton(checked = expand, onCheckedChange = {expand = !expand}) {
                                      if (expand){
                                          Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null)
                                      } else  {
                                          Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24) , contentDescription = null)
                                      }
                                  }
                                  IconButton(onClick = {
                                      scope.launch {
                                          try {
                                              viewModel.deletetodo(note.id)
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
                            if (expand){
                                Text(text = note.note)
                            }
                        }
                    }
                }
            }

        }
    }

}