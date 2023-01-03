package com.example.jetsnack.database

import android.util.Log
import androidx.annotation.DrawableRes
import com.example.jetsnack.R
import com.example.jetsnack.model.userme
import com.example.jetsnack.repository.AuthorRepo
import com.firebase.ui.auth.data.model.User.getUser
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class User(
    var id: String,
    val key: Int,
    var name: String,
    var equipDog: Int,
    var achievement: ArrayList<Int>,
    var own_dogs: ArrayList<Int>,
    var introduction: String,
    var money: Int,
    var continue_focus_time: Int,
    var total_focus_time: Int,
    var friend_number: Int,
    var continue_login_day: Int,
    var last_focus_time: Int,
    var day_focus_time: Int,
    var notification: Boolean,
    var new_friend: Boolean,
    var moon: Boolean,
)

var user = User(
    id = "abcd",
    key = 1877,
    name = "apple",
    equipDog = 0,
    achievement = ArrayList(),
    own_dogs = ArrayList(),
    introduction = "Hi",
    money = 1500,
    continue_focus_time = 1,
    total_focus_time = 12600,
    friend_number = 12,
    continue_login_day = 1,
    last_focus_time = 1,
    day_focus_time = 1,
    notification = false,
    new_friend = true,
    moon = true
)

@OptIn(DelicateCoroutinesApi::class)
suspend fun UpdateUser() {
    val authorRepo = AuthorRepo()
    GlobalScope.launch(Dispatchers.Main) {
        authorRepo.updateName(user.id , user.name)
        authorRepo.updateEquipDog(user.id, user.equipDog)
        authorRepo.updateMoney(user.id , user.money)
        authorRepo.updateIntroduction(user.id , user.introduction)
        authorRepo.update_continue_focus_time(user.id , user.continue_focus_time)
        authorRepo.update_total_focus_time(user.id , user.total_focus_time)
        authorRepo.update_friend_number(user.id , user.friend_number)
        authorRepo.update_continue_login_day(user.id , user.continue_login_day)
        authorRepo.update_day_focus_time(user.id , user.day_focus_time)
        authorRepo.update_last_focus_time(user.id , user.last_focus_time)
        authorRepo.updateNotification(user.id , user.notification)
        authorRepo.updateMeetingFriend(user.id , user.new_friend)
        authorRepo.updateDisturdMode(user.id , user.moon)

    }


}