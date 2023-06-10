package com.example.bmicalculator.Usecase

import androidx.compose.ui.text.font.FontWeight

class Usecase {

      fun calculatebmi (height : Float,weight: Float) : Float{
          var hght  = height / 100
          var bmi  = weight / (hght * hght)

          return bmi
     }

     fun healthstat (bmiscore : Float) : String{
         if (bmiscore < 18.5){
             return "Underweight"
         }

         if (bmiscore < 25.0){
             return "Healthy"
         }

         if (bmiscore < 30.0){
             return "Overweight"
         }

         return "Obese"
     }


}