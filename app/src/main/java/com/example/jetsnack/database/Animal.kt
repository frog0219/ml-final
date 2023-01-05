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
        intro1 = "特徵:老橘、短尾、有剪耳",
        intro2 = "說明:最近瘋狂變胖....",
        intro3 = "出沒地點:教育館"
    ),
    Animal(
        id = 2,
        name = "牛排",
        picture = R.drawable.animal_02 ,
        intro1 = "特徵:黑狗，有白色斑紋，看起來像隻乳牛",
        intro2 = "說明:其實是交大的狗，但幾乎都在清大",
        intro3 = "出沒地點:全校"
    ),
    Animal(
        id = 3,
        name = "巧巧",
        picture = R.drawable.animal_06 ,
        intro1 = "特徵:土黃色，黑臉",
        intro2 = "說明:非常親人，喜愛跟人互動",
        intro3 = "出沒地點:全校"

    ),
    Animal(
        id = 4,
        name = "小白",
        picture = R.drawable.animal_07 ,
        intro1 = "特徵:白色，優雅",
        intro2 = "說明:性格較為高冷，親不親人要看心情",
        intro3 = "出沒地點:台積館"
    ),
    Animal(
        id = 5,
        name = "花捲",
        picture = R.drawable.animal_33 ,
        intro1 = "特徵:乳牛配色，捲尾",
        intro2 = "說明:極度怕生，不親人，但很貪吃",
        intro3 = "出沒地點:義齋側門，宿舍區"
    ),
    Animal(
        id = 6,
        name = "黑胖",
        picture = R.drawable.animal_08 ,
        intro1 = "特徵:黑色，雙垂耳",
        intro2 = "說明:親人但容易受驚，曾經乖狗的小弟",
        intro3 = "出沒地點:台積館"
    ),
    Animal(
        id = 7,
        name = "乖狗",
        picture = R.drawable.animal_09 ,
        intro1 = "特徵:黑色，體型圓胖，一立一垂耳",
        intro2 = "說明:非常親人，喜歡被拍屁股跟翻肚",
        intro3 = "出沒地點:全校，宵夜街"
    ),
    Animal(
        id = 8,
        name = "橘子",
        picture = R.drawable.animal_10 ,
        intro1 = "特徵:橘色虎班紋",
        intro2 = "說明:偶爾在台積館，不太親人",
        intro3 = "出沒地點:台積館"
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
    ),
    Animal(
        id = 12,
        name = "無法預測",
        picture = R.drawable.animal_14 ,
        intro1 = "",
        intro2 = "",
        intro3 = ""
    )

)