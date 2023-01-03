package com.example.jetsnack.database

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.jetsnack.R

data class Animal(
    val id: Long,
    val name: String,
    @DrawableRes
    val picture:Int,
    val intro1 : String,
    val intro2 : String,
    val intro3 : String
)
val Animals = listOf(
    Animal(
        id = 1,
        name = "森森",
        picture = R.drawable.animal_01 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 2,
        name = "牛排",
        picture = R.drawable.animal_02 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 3,
        name = "巧巧",
        picture = R.drawable.animal_06 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""

    ),
    Animal(
        id = 4,
        name = "小白",
        picture = R.drawable.animal_07 ,
        intro1 = "從小就愛bully別人~~",
        intro2 = "我的毒沒有解藥",
        intro3 = "快哭吧，孩子"
    ),
    Animal(
        id = 5,
        name = "花捲",
        picture = R.drawable.animal_33 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 6,
        name = "黑胖",
        picture = R.drawable.animal_08 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 7,
        name = "乖狗",
        picture = R.drawable.animal_09 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 8,
        name = "橘子",
        picture = R.drawable.animal_10 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 9,
        name = "烏龜",
        picture = R.drawable.animal_11__3778,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 10,
        name = "松鼠",
        picture = R.drawable.animal_13 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Animal(
        id = 11,
        name = "笨鳥",
        picture = R.drawable.animal_14 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    )
)