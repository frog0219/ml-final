package com.example.jetsnack.model

import android.os.Parcelable
import com.example.jetsnack.messages.LatestMessagesActivity
import kotlinx.android.parcel.Parcelize

//User model class
@Parcelize
class User(val uid: String, val username: String, val profileImageUrl:String ,val friend:ArrayList<String>) : Parcelable {
    constructor() : this("", "", "", arrayListOf(""))
}
var userme=LatestMessagesActivity.currentUser