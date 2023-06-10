package com.example.bmicalculator

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import com.example.bmicalculator.Usecase.Usecase
import com.example.bmicalculator.ui.theme.Windowinfo
import com.example.bmicalculator.ui.theme.rememberwindowinfo
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIcalculator (viewmodel: Viewmodel) {

   var windowinfo = rememberwindowinfo()
   var height  = viewmodel.height
    var weight = viewmodel.weight
   var heightlabel = viewmodel.heightlabel
   var weightlabel = viewmodel.weightlabel
   var dropdownheight  by remember { mutableStateOf(false)}
    var dropdownweight  by remember { mutableStateOf(false)}
  var bmiscore = viewmodel.bmiscore
    var healthStats = viewmodel.healthStats
  var colors =  viewmodel.colors
  var dismiss = viewmodel.dismiss
  var context  = LocalContext.current

  when(healthStats.value){
     "Underweight" -> {
         colors.value = "#7268ff"
     }
      "Healthy" -> {
          colors.value = "#004D40"
      }
      "Overweight"  -> {
          colors.value = "#F57F17"
      }
      "Obese" -> {colors.value = "#fe1544"}

      else -> {}

  }

    if ( windowinfo.screenWidthinfo is Windowinfo.Windowtype.Compact) {
       Scaffold(
           topBar = {
               Row(modifier = Modifier
                   .fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                   Text(text = "BMI calculator",modifier = Modifier.padding(top = 10.dp,start = 20.dp),style = TextStyle(color = Color.White, fontSize = 25.sp))
               }
           }
       ) {
          Box(modifier = Modifier
              .fillMaxSize()
              .background(
                  color = Color(colors.value.toColorInt())
              )
              .padding(it)) {
              Column(modifier = Modifier.padding(top = 30.dp)) {
                  Column() {
                      Text(text = "Height: ", modifier = Modifier.padding(start = 15.dp))
                      Spacer(modifier = Modifier.height(10.dp))
                      Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                          TextField(value = height.value, onValueChange ={height.value  = it},
                              label = { Text(text = heightlabel.value)},
                              modifier = Modifier
                                  .padding(start = 5.dp)
                                  .width(200.dp)
                          )
                          Spacer(modifier = Modifier.width(20.dp))
                          Column(modifier = Modifier.padding(end = 5.dp)) {
                              TextField(value = heightlabel.value, onValueChange = {heightlabel.value = it},
                                  trailingIcon = {
                                      IconToggleButton(checked = dropdownheight, onCheckedChange ={dropdownheight = it}) {
                                          if (dropdownheight){
                                              Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null)
                                          } else {
                                              Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24), contentDescription = null)
                                          }
                                      }
                                  }, label = { Text(text = "Units")}
                              )
                              DropdownMenu(expanded = dropdownheight, onDismissRequest = { dropdownheight = false}) {
                                  DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                                      heightlabel.value  = "Centimeter"
                                      dropdownheight  = false
                                  })
                              }
                          }

                      }
                  }
                  Spacer(modifier = Modifier.height(20.dp))
                  Column(modifier = Modifier.padding(end = 5.dp)) {
                      Text(text = "Weight : ", modifier = Modifier.padding(start = 10.dp))
                      Spacer(modifier = Modifier.height(10.dp))
                      Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                          TextField(value = weight.value, onValueChange ={weight.value  = it},
                              label = { Text(text = weightlabel.value)},
                              modifier = Modifier
                                  .padding(start = 5.dp)
                                  .width(200.dp)
                          )
                          Spacer(modifier = Modifier.width(20.dp))
                          Column() {
                              TextField(value = weightlabel.value, onValueChange = {weightlabel.value = it},
                                  trailingIcon = {
                                      IconToggleButton(checked = dropdownweight, onCheckedChange ={dropdownweight = it}) {
                                          if (dropdownweight){
                                              Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null)
                                          } else {
                                              Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24), contentDescription = null)
                                          }
                                      }
                                  }, label = { Text(text = "Units")}
                              )
                              DropdownMenu(expanded = dropdownweight, onDismissRequest = { dropdownweight = false}) {
                                  DropdownMenuItem(text = {Text(text = "Kilograms")}, onClick = {
                                      weightlabel.value  = "Kilograms"
                                      dropdownweight  = false
                                  })
                              }
                          }

                      }
                  }
                  Spacer(modifier = Modifier.height(20.dp))
                  Button(onClick = {
                       if (height.value.isEmpty() || weight.value.isEmpty() || heightlabel.value.isEmpty() || weightlabel.value.isEmpty()){
                           Toast.makeText(context,"All fields are required",Toast.LENGTH_LONG).show()
                       } else {
                           var usecase  = Usecase()
                           bmiscore.value = usecase.calculatebmi(height.value.toFloat(),weight.value.toFloat())
                           healthStats.value = usecase.healthstat(bmiscore.value)
                           dismiss.value = true
                       }

                  }, colors = ButtonDefaults.buttonColors(Color("#f58442".toColorInt())), modifier = Modifier.padding(start = 20.dp)) {
                      Text(text = "Calculate")
                  }
              }
              Column(modifier = Modifier
                  .align(Alignment.BottomCenter)
                  .fillMaxWidth()) {
                 BMIdialog(dismiss = dismiss.value, onDismiss = { dismiss.value  = false }, bmiscore = bmiscore.value, healthstatus = healthStats.value)
              }
          }
       }

   }  else if (windowinfo.screenWidthinfo is Windowinfo.Windowtype.Medium){
        Scaffold(
            topBar = {
                Row(modifier = Modifier
                    .fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "BMI calculator",modifier = Modifier.padding(top = 10.dp,start = 20.dp),style = TextStyle(color = Color.White, fontSize = 25.sp))
                }
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(colors.value.toColorInt())
                )
                .padding(it)) {
                Column(modifier = Modifier.padding(top = 30.dp)) {
                    Column() {
                        Text(text = "Height: ", modifier = Modifier.padding(start = 15.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            TextField(value = height.value, onValueChange ={height.value  = it},
                                label = { Text(text = heightlabel.value)},
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .width(200.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(modifier = Modifier.padding(end = 5.dp)) {
                                TextField(value = heightlabel.value, onValueChange = {heightlabel.value = it},
                                    trailingIcon = {
                                        IconToggleButton(checked = dropdownheight, onCheckedChange ={dropdownheight = it}) {
                                            if (dropdownheight){
                                                Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null)
                                            } else {
                                                Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24), contentDescription = null)
                                            }
                                        }
                                    }, label = { Text(text = "Units")}
                                )
                                DropdownMenu(expanded = dropdownheight, onDismissRequest = { dropdownheight = false}) {
                                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                                        heightlabel.value  = "Centimeter"
                                        dropdownheight  = false
                                    })
                                }
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.padding(end = 5.dp)) {
                        Text(text = "Weight : ", modifier = Modifier.padding(start = 10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            TextField(value = weight.value, onValueChange ={weight.value  = it},
                                label = { Text(text = weightlabel.value)},
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .width(200.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Column() {
                                TextField(value = weightlabel.value, onValueChange = {weightlabel.value = it},
                                    trailingIcon = {
                                        IconToggleButton(checked = dropdownweight, onCheckedChange ={dropdownweight = it}) {
                                            if (dropdownweight){
                                                Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = null)
                                            } else {
                                                Icon(painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24), contentDescription = null)
                                            }
                                        }
                                    }, label = { Text(text = "Units")}
                                )
                                DropdownMenu(expanded = dropdownweight, onDismissRequest = { dropdownweight = false}) {
                                    DropdownMenuItem(text = {Text(text = "Kilograms")}, onClick = {
                                        weightlabel.value  = "Kilograms"
                                        dropdownweight  = false
                                    })
                                }
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        if (height.value.isEmpty() || weight.value.isEmpty() || heightlabel.value.isEmpty() || weightlabel.value.isEmpty()){
                            Toast.makeText(context,"All fields are required",Toast.LENGTH_LONG).show()
                        } else {
                            var usecase  = Usecase()
                            bmiscore.value = usecase.calculatebmi(height.value.toFloat(),weight.value.toFloat())
                            healthStats.value  = usecase.healthstat(bmiscore.value)
                            dismiss.value = true
                        }

                    }, colors = ButtonDefaults.buttonColors(Color("#f58442".toColorInt())), modifier = Modifier.padding(start = 20.dp)) {
                        Text(text = "Calculate")
                    }
                }
                Column(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()) {
                    BMIdialog(dismiss = dismiss.value, onDismiss = { dismiss.value  = false }, bmiscore = bmiscore.value, healthstatus = healthStats.value)
                }
            }
        }

    }



}