package com.example.jetsnack.ui.home

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

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.database.Friend
import com.example.jetsnack.database.Friends
import com.example.jetsnack.model.ListOfAchieve
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*

@Composable
fun CheckUpFriend(modifier: Modifier = Modifier, navController: NavHostController, id: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .height(700.dp)
                .padding(20.dp)

        ) {
            /*Spacer(Modifier.height(48.dp))*/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                ) {
                    Image(
                        painterResource(R.drawable.arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.popBackStack() }
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(610.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Shadow1)
                    .padding(15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(125.dp)
                        .height(125.dp)
                ) {
                    Image(
                        painterResource(Dogs[Friends[id].equipDog].picture),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(text = Friends[id].name, color = Ocean11, fontSize = 30.sp)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "自我介紹", color = Shadow4, fontSize = 18.sp)
                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable { }
                        .clip(RoundedCornerShape(20.dp))
                        .background(Shadow2)
                        .padding(10.dp)) {
                        Text(
                            text = Friends[id].introduction,
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(text = "近期成就", color = Shadow4, fontSize = 18.sp)
                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clickable { }
                        .clip(RoundedCornerShape(20.dp))
                        .background(Shadow2)
                        .padding(10.dp)) {
                        for (i in 0 until Friends[id].achievement.size - 1) {
                            val num = if(Friends[id].achievement[i] > 12)1 else 0 + if(Friends[id].achievement[i] > 21)1 else 0
                            val index = Friends[id].achievement[i] - (if(num > 0) 12 else 0) - (if(num == 2) 9 else 0) - 1
                            Box(modifier = modifier.size(45.dp)) {
                                Image(painterResource(ListOfAchieve[num][index].picture_own),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }

        }
    }
}

