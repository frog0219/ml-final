package com.example.jetsnack.database

import com.example.jetsnack.model.FocusRoom
import kotlinx.android.parcel.Parcelize

data class mutiple_class(
    var Isfull : Boolean,
    var START :Boolean,
    var nowTime : Int,
    var correct : Boolean
)
val MutipleFocus = mutiple_class(Isfull = false , START =false , nowTime = 0 , correct = false)
var focusRoom = FocusRoom("aaaaa","" , "", "","hyi",false,900)
