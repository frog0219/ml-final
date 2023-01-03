package com.example.jetsnack.database



data class Hatch(
   var remain_time: Double,
   val id: Int
)


val Hatchs = arrayListOf<Hatch>(
   Hatch(
      remain_time = 15.0,
      id = 4
   ),
   Hatch(
      remain_time = 10.0,
      id = 3
   )
)