/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.model.Achievement
import com.example.jetsnack.model.NormalAchievement
import com.example.jetsnack.model.User
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun CompleteFocus(modifier: Modifier = Modifier, navController: NavHostController, total: Int) {
    val coin = total / 300 + 10
    NormalAchievement[5].own = true
    var time : Double = remember {
        total.toDouble() / 3600.0
    }
    Hatchs.forEach{ egg->
        egg.remain_time -= time
    }
    time = 0.0
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(48.dp))
        Text(text = "Congratulations!", fontSize = 40.sp)
        Spacer(Modifier.height(64.dp))
        Text(text = "You achieved your goal!", fontSize = 24.sp)
        Column(
            modifier = Modifier.height(400.dp)
        ) {
            Image(
                painterResource(R.drawable.manwithdog),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Button(
            onClick = {
                val id = GetNewFriendid()
                user.money += coin
                GlobalScope.launch(Dispatchers.Main) {
                    UpdateUser()
                }
                navController.navigate("meetNewFriend/$id")
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
            colors = ButtonDefaults.buttonColors(backgroundColor = FunctionalGreen),
        ) {
            Text("獲得$coin ", color = Ocean11, fontSize = 20.sp)
            Column(modifier = Modifier.height(24.dp)) {
                Image(
                    painterResource(R.drawable.coin),
                    contentDescription = null,
                )
            }

        }
    }
}

