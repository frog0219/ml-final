package com.example.jetsnack.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FocusRoom(val id1: String, val id2: String, val id3:String, val id4:String, val roomId:String, val start:Boolean,
                var sec:Int) : Parcelable {
    constructor() : this("", "", "", "","",false,0)
}