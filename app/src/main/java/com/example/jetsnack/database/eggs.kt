package com.example.jetsnack.database

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.jetsnack.R
import com.example.jetsnack.ui.theme.Shadow4

data class Egg(
    val name: String,
    val price: Int,
    @DrawableRes
    val picture:Int,
    val color : Long,
    val time : Double
)
val Eggs = listOf(
    Egg(
        name = "1小時",
        price = 50 ,
        picture = R.drawable.egg_1 ,
        color = 0xffd6fefe,
        time = 1.0
    ),
    Egg(
        name = "2小時",
        price = 100,
        picture = R.drawable.egg_2,
        color = 0x30f8a44c,
        time = 2.0
    ),
    Egg(
        name = "4小時",
        price = 250,
        picture = R.drawable.egg_3,
        color = 0x30f7a593,
        time = 4.0
    ),
    Egg(
        name = "10小時",
        price = 500,
        picture = R.drawable.egg_4,
        color = 0x307fa593,
        time = 10.0
    ),
    Egg(
        name = "16小時",
        price = 1000,
        picture = R.drawable.egg_5,
        color = 0x30d3b0e0,
        time = 16.0
    ),
    Egg(
        name = "24小時",
        price = 1250,
        picture = R.drawable.egg_6,
        color = 0xffded6fe,
        time = 24.0
    ),
    Egg(
        name = "32小時",
        price = 1800,
        picture = R.drawable.egg_7,
        color = 0x3097005c,
        time = 32.0
    ),
    Egg(
        name = "64小時",
        price = 2000,
        picture = R.drawable.egg_8,
        color = 0x30f8a44c,
        time = 64.0
    )
)
