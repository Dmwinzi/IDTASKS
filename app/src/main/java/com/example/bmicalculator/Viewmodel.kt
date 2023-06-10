package com.example.bmicalculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel

class Viewmodel : ViewModel () {


    var height   = mutableStateOf("")
    var weight = mutableStateOf("")
    var heightlabel  = mutableStateOf("")
    var weightlabel = mutableStateOf("")
    var colors = mutableStateOf("#7268ff")
    var dismiss  = mutableStateOf(false)
    var bmiscore  = mutableStateOf(0f)
    var healthStats  = mutableStateOf("")

}