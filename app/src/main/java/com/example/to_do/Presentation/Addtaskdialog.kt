package com.example.to_do.Presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import com.example.to_do.Data.Entity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addtaskdialog(context: Context,viewModel: Mainviewmodel,dismiss: Boolean, onDismiss : () -> Unit ){

    var title by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}
    var scope = rememberCoroutineScope()

    if (dismiss){
        Dialog(onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = true,dismissOnClickOutside = true)
        )
        {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(300.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
                Text(text = "Add task", modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 15.dp) ,style = TextStyle(color = Color.Black,fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )

                Column(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(value = title, onValueChange = {title = it}, label = { Text(
                        text = "Title"
                    )}, singleLine = true)

                    OutlinedTextField(value = description, onValueChange = {description = it}, label = { Text(
                        text = "description"
                    )}, modifier = Modifier.height(150.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        if (title.isEmpty() || description.isEmpty()){
                            Toast.makeText(context,"All fields are required", Toast.LENGTH_LONG).show()
                        } else {
                            scope.launch {
                                try {
                                    var entity  = Entity(0,title,description)
                                    viewModel.inserttodo(entity)
                                    onDismiss()
                                    Toast.makeText(context,"Task Updated succesfully", Toast.LENGTH_LONG).show()
                                }catch (e : Exception){
                                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }) {
                        Text(text = "Add task")
                    }
                }
            }
        }
    }


}