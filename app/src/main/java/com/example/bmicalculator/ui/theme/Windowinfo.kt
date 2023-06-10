package com.example.bmicalculator.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberwindowinfo () : Windowinfo {

    var configuration  = LocalConfiguration.current

    return Windowinfo(
        screenWidthinfo = when{
            configuration.screenWidthDp < 600 -> Windowinfo.Windowtype.Compact
            configuration.screenWidthDp < 840 -> Windowinfo.Windowtype.Medium
            else -> Windowinfo.Windowtype.Expanded
        },
        screenHeightinfo = when{
            configuration.screenHeightDp < 480 -> Windowinfo.Windowtype.Compact
            configuration.screenHeightDp < 900 -> Windowinfo.Windowtype.Medium
            else -> Windowinfo.Windowtype.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp,
    )
}

data class Windowinfo(
    val screenWidthinfo: Windowtype,
    val screenHeightinfo: Windowtype,
    val screenWidth: Dp,
    val screenHeight: Dp,
){
    sealed class Windowtype{
        object Compact : Windowtype()
        object Medium : Windowtype()
        object Expanded : Windowtype()
    }

}