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

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.database.user
import com.example.jetsnack.registerLogin.RegisterActivity
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PersonalInformation(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .height(800.dp)
                .padding(20.dp)

        ) {
            /*Spacer(Modifier.height(48.dp))*/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                Text(text = "", fontSize = 24.sp)
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                ) {
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(modifier = Modifier.width(320.dp)) {
                Text(text = "個人資料", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(320.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        Shadow4
                    )
                    .padding(10.dp)
            ) {
                Box(
                    modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(3.dp)
                ) {
                    Image(
                        painterResource(Dogs[user.equipDog].picture),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(Modifier.width(10.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = user.name, color = Color.White, fontSize = 18.sp)
                    Text(
                        text = "@${user.name} #${user.id.subSequence(0, 6)}",
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .width(320.dp)
                    .height(275.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        Color.White
                    )
                    .padding(10.dp)
            ) {
                Text(text = "自我介紹", fontSize = 14.sp)
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Shadow1
                        )
                        .padding(0.dp)
                        .padding(10.dp)
                ) {
                    Text(text = user.introduction, color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(Modifier.height(10.dp))
                Text(text = "成就", fontSize = 14.sp)
                Spacer(Modifier.height(10.dp))
                Row(
                ) {
                    Box(
                        modifier
                            .size(40.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.heart),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Box(
                        modifier
                            .size(40.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.support),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
            Spacer(Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "More", fontSize = 18.sp)
                Spacer(Modifier.height(10.dp))
                P_I_row(image = R.drawable.account, text1 = "帳號", text2 = "變更帳號資料")
                Spacer(Modifier.height(10.dp))
                P_I_row(image = R.drawable.logout, text1 = "登出", text2 = "登出您的帳號")
            }

        }
    }
}

@Preview("13", showBackground = true)

@Composable
fun PersonalInformationPreview() {
    JetsnackTheme {
        //PersonalInformation()
    }
}

@Composable
fun P_I_row(image: Int, text1: String, text2: String) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp),
            ) {
                Image(
                    painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(Modifier.width(16.dp))
            Column() {
                Text(text = text1, fontSize = 18.sp)
                Spacer(Modifier.height(4.dp))
                Text(text = text2, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Row() {
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable {
                    },
            ) {
                Image(
                    painterResource(R.drawable.arrow2),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (text1 == "登出") {
                                FirebaseAuth
                                    .getInstance()
                                    .signOut()
                                context.startActivity(Intent(context, RegisterActivity::class.java))
                            } else {

                            }
                        }
                )
            }
            Spacer(Modifier.width(10.dp))
        }

    }

}