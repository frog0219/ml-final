package com.example.jetsnack.database


import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.jetsnack.R

data class Dog(
    val id: Long,
    val name: String,
    var own: Boolean,
    val egg:Int,
    @DrawableRes
    val picture:Int,
    val intro1 : String,
    val intro2 : String,
    val intro3 : String
)
var Select_index = user.equipDog
val Dogs = listOf(
    Dog(
        id = 1,
        name = "Margaret",
        own = true ,
        picture = R.drawable.animal_01 ,
        egg = 1,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 2,
        name = "Mandy",
        own = false ,
        picture = R.drawable.animal_02 ,
        egg = 1,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 3,
        name = "Eric",
        own = true ,
        picture = R.drawable.animal_06 ,
        egg = 2,
        intro1 = "",
        intro2 = "",
        intro3 = ""

    ),
    Dog(
        id = 4,
        name = "Billy",
        own = true ,
        picture = R.drawable.animal_07 ,
        egg = 2,
        intro1 = "從小就愛bully別人~~",
        intro2 = "我的毒沒有解藥",
        intro3 = "快哭吧，孩子"
    ),
    Dog(
        id = 5,
        name = "Una",
        own = false ,
        picture = R.drawable.animal_33 ,
        egg = 3,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 6,
        name = "Johnny",
        own = false ,
        picture = R.drawable.animal_08 ,
        egg = 3,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 7,
        name = "Barbara",
        own = false ,
        picture = R.drawable.animal_09 ,
        egg = 4,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 8,
        name = "Robin",
        own = false ,
        picture = R.drawable.animal_10 ,
        egg = 4,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 9,
        name = "Ray",
        own = false ,
        picture = R.drawable.animal_11__3778,
        egg = 5,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 10,
        name = "john",
        own = false ,
        picture = R.drawable.animal_13 ,
        egg = 5,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 11,
        name = "john",
        own = false ,
        picture = R.drawable.animal_14 ,
        egg = 6,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 12,
        name = "john",
        own = false ,
        picture = R.drawable.animal_17 ,
        egg = 6,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 13,
        name = "john",
        own = false ,
        picture = R.drawable.animal_20 ,
        egg = 7,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 14,
        name = "john",
        own = false ,
        picture = R.drawable.animal_22 ,
        egg = 7,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 15,
        name = "john",
        own = false ,
        picture = R.drawable.animal_26 ,
        egg = 8,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    ),
    Dog(
        id = 16,
        name = "Badboy",
        own = false ,
        picture = R.drawable.animal_30 ,
        egg = 8,
        intro1 = "「我和她只是朋友」",
        intro2 = "「我不渣，只是想給每個女孩一個家」",
        intro3 = "「抓不住我的心…就別說我花心」"
    ),

)