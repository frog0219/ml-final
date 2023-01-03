package com.example.jetsnack.database

import android.util.Log
import androidx.annotation.DrawableRes
import com.example.jetsnack.R
import com.example.jetsnack.model.Achievement
import com.example.jetsnack.model.User
import com.example.jetsnack.repository.AuthorRepo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

data class Friend(
    val id: String,
    val name: String,
    val equipDog: Int,
    val achievement: ArrayList<Int>,
    val introduction: String,
    val sequence: Int
)

val Users = arrayListOf<com.example.jetsnack.model.User>()
val Friends = arrayListOf<Friend>()
var NewFriend = arrayListOf<Friend>(
    Friend(
        id = "wC4bKzTLXRMSkBPAZUrdGUCRDvq2",
        name = "Victoria",
        equipDog = 3,
        achievement = arrayListOf(1, 4, 5, 12),
        introduction = "fuck you",
        sequence = 0
    ),
    Friend(
        id = "FvudLOxqQ9V3h9L18UNrs2g8xb92",
        name = "frog",
        equipDog = 1,
        achievement = arrayListOf(1, 4, 7, 10, 16),
        introduction = "fuck you",
        sequence = 0
    ),
    Friend(
        id = "VpMuJQJSFTdC7xQuv6WEV4EXVTM2",
        name = "Froggy",
        equipDog = 0,
        achievement = arrayListOf(3, 9, 6, 19, 17),
        introduction = "呱呱呱呱呱呱呱呱呱呱",
        sequence = 0
    ),
    Friend(
        id = "uD2va5LRk5N4S5qXDDiwyykc7292",
        name = "Betty",
        equipDog = 13,
        achievement = arrayListOf(1, 2, 4,  13),
        introduction = "fuck you",
        sequence = 0
    ),

)














/*Friend(
    id = 1,
    sequence = 0,
    name = "Dora",
    equipDog = 9,
    achievement = arrayListOf(1, 9, 13, 1, 29),
    introduction = "這個人很懶，什麼都沒留下..."
),
Friend(
    id = 2,
    sequence = 1,
    name = "John",
    equipDog = 10,
    achievement = arrayListOf(1, 9, 13, 1, 29),
    introduction = "這個人很懶，什麼都沒留下..."
),
Friend(
    id = 3,
    sequence = 2,
    name = "Danny",
    equipDog = 5,
    achievement = arrayListOf(1, 9, 13, 1, 29),
    introduction = "這個人很懶，什麼都沒留下..."
),
Friend(
    id = 4,
    sequence = 3,
    name = "K",
    equipDog = 6,
    achievement = arrayListOf(1, 9, 13, 1, 29),
    introduction = "這個人很懶，什麼都沒留下..."
),
Friend(
    id = 5,
    sequence = 4,
    name = "Jerry",
    equipDog = 4,
    achievement = arrayListOf(1, 9, 13, 1, 29),
    introduction = "這個人很懶，什麼都沒留下..."
),*/

