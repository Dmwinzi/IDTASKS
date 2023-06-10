package com.example.bmicalculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.toColorInt

@Composable
fun BMIdialog(dismiss : Boolean,onDismiss : () -> Unit,bmiscore : Float,healthstatus  : String){

    if (dismiss){
        Dialog(onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = true,dismissOnClickOutside = true)
        )
        {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(150.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
                  Text(text = "BMI score", color = Color.Black,modifier = Modifier
                      .align(Alignment.TopStart)
                      .padding(start = 10.dp, top = 5.dp))
                  Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(
                      Alignment.Center)) {
                      Text(text = bmiscore.toString(),fontSize = 35.sp,color = Color("#f58442".toColorInt()))
                       Spacer(modifier = Modifier.width(25.dp))
                      Text(text = healthstatus, color = Color.Black,fontSize = 20.sp)

                  }

                }
            }
        }
}