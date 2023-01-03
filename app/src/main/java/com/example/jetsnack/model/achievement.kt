package com.example.jetsnack.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.jetsnack.R

@Immutable
data class Achievement(
    val name: String,
    val target: Int,
    @DrawableRes
    val picture_own: Int,
    val picture_lost: Int,
    var own: Boolean
)

val NormalAchievement = listOf(
    Achievement(
        name = "萍水相逢",
        target = 1,
        picture_own = R.drawable.friend1,
        picture_lost = R.drawable.friend1_n,
        own = true
    ),
    Achievement(
        name = "開心,擊掌",
        target = 5,
        picture_own = R.drawable.friend2,
        picture_lost = R.drawable.friend2_n,
        own = false
    ),
    Achievement(
        name = "左右逢源",
        target = 12,
        picture_own = R.drawable.friend3,
        picture_lost = R.drawable.friend3_n,
        own = false
    ),
    Achievement(
        name = "地球村",
        target = 30,
        picture_own = R.drawable.friend4,
        picture_lost = R.drawable.friend4_n,
        own = false
    ),
    Achievement(
        name = "初出茅廬",
        target = 1,
        picture_own = R.drawable.focus1,
        picture_lost = R.drawable.focus1_n,
        own = true
    ),
    Achievement(
        name = "戰鬥吧",
        target = 20,
        picture_own = R.drawable.focus2,
        picture_lost = R.drawable.focus2_n,
        own = false
    ),
    Achievement(
        name = "專注達人",
        target = 60,
        picture_own = R.drawable.focus3,
        picture_lost = R.drawable.focus3_n,
        own = false
    ),
    Achievement(
        name = "老專寶了!",
        target = 150,
        picture_own = R.drawable.focus4,
        picture_lost = R.drawable.focus4_n,
        own = false
    ),
    Achievement(
        name = "骨頭,好吃",
        target = 1,
        picture_own = R.drawable.dog1,
        picture_lost = R.drawable.dog1_n,
        own = false
    ),
    Achievement(
        name = "愛狗人士",
        target = 3,
        picture_own = R.drawable.dog2,
        picture_lost = R.drawable.dog2_n,
        own = false
    ),
    Achievement(
        name = "狗狗大集團",
        target = 8,
        picture_own = R.drawable.dog3,
        picture_lost = R.drawable.dog3_n,
        own = false
    ),
    Achievement(
        name = "好大的狗屋",
        target = 15,
        picture_own = R.drawable.dog4,
        picture_lost = R.drawable.dog4_n,
        own = false
    )

)

val SpecialAchievement = listOf(
    Achievement(
        name = "六的一匹",
        target = 6,
        picture_own = R.drawable.continue1,
        picture_lost = R.drawable.continue1_n,
        own = false
    ),
    Achievement(
        name = "斯巴達勇士",
        target = 8,
        picture_own = R.drawable.continue2,
        picture_lost = R.drawable.continue2_n,
        own = false
    ),
    Achievement(
        name = "爆肝人生",
        target = 16,
        picture_own = R.drawable.continue3,
        picture_lost = R.drawable.continue3_n,
        own = false
    ),
    Achievement(
        name = "專注,睡覺?",
        target = 24,
        picture_own = R.drawable.continue4,
        picture_lost = R.drawable.continue4_n,
        own = false
    ),
    Achievement(
        name = "持之以恆",
        target = 1,
        picture_own = R.drawable.login1,
        picture_lost = R.drawable.login1_n,
        own = true
    ),
    Achievement(
        name = "永續發展",
        target = 20,
        picture_own = R.drawable.login2_n,
        picture_lost = R.drawable.login2_n,
        own = false
    ),
    Achievement(
        name = "越來越好",
        target = 60,
        picture_own = R.drawable.all1,
        picture_lost = R.drawable.all1_n,
        own = false
    ),
    Achievement(
        name = "頂尖專注者",
        target = 150,
        picture_own = R.drawable.all2,
        picture_lost = R.drawable.all2_n,
        own = false
    ),
    Achievement(
        name = "GODLIKE",
        target = 1,
        picture_own = R.drawable.all3,
        picture_lost = R.drawable.all3_n,
        own = false
    ),
)
val FestivalAchievement = listOf(
    Achievement(
        name = "專注聖誕節",
        target = 6,
        picture_own = R.drawable.chrismas,
        picture_lost = R.drawable.chrismas_n,
        own = false
    ),
    Achievement(
        name = "感恩惜福",
        target = 8,
        picture_own = R.drawable.thanks,
        picture_lost = R.drawable.thanks_n,
        own = false
    ),
    Achievement(
        name = "彩蛋,兔子?",
        target = 16,
        picture_own = R.drawable.easter_bunny,
        picture_lost = R.drawable.easter_bunny_n,
        own = false
    ),
    Achievement(
        name = "沒心啦",
        target = 24,
        picture_own = R.drawable.heart,
        picture_lost = R.drawable.heart_n,
        own = false
    ),
    Achievement(
        name = "恭喜發財",
        target = 1,
        picture_own = R.drawable.newyear,
        picture_lost = R.drawable.newyear_n,
        own = false
    ),
    Achievement(
        name = "飯糰?",
        target = 20,
        picture_own = R.drawable.rice,
        picture_lost = R.drawable.rice_n,
        own = false
    ),
    Achievement(
        name = "迢迢牽牛星",
        target = 60,
        picture_own = R.drawable.moon_cake,
        picture_lost = R.drawable.moon_cake_n,
        own = false
    ),
    Achievement(
        name = "#哈們",
        target = 150,
        picture_own = R.drawable.marijuana,
        picture_lost = R.drawable.marijuana_n,
        own = false
    ),
    Achievement(
        name = "專注大日子",
        target = 1,
        picture_own = R.drawable.focusday,
        picture_lost = R.drawable.focusday_n,
        own = false
    ),
)
val ListOfAchieve = listOf(
    SpecialAchievement,
    NormalAchievement,
    FestivalAchievement
)
