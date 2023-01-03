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

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.model.User
import com.example.jetsnack.model.userme
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MeetNewFriend(modifier: Modifier = Modifier, navController: NavHostController , id :Int) {
    var message by remember { mutableStateOf("") }
    var showAlertDialog1 by remember { mutableStateOf(false) }
    var showAlertDialog2 by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        /*Spacer(Modifier.height(48.dp))*/
        Text(text = "哇!遇到新朋友了...", fontSize = 32.sp, color = Shadow3)
        Spacer(Modifier.height(8.dp))
        Text(text = NewFriend[id].name, fontSize = 36.sp, color = Shadow3)
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .background(Shadow1)
                .clickable { navController.navigate("CheckUpNewFriend/$id") }
                .padding(0.dp)
                .padding(40.dp)
        ) {
            Image(
                painterResource(Dogs[NewFriend[id].equipDog].picture),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.height(8.dp))
        TextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text(text = "send a message...", color = Color(0x50000000)) },
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(10.dp)
                .width(275.dp)
                .height(125.dp)
                .background(Shadow1),
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .width(100.dp)
                    .height(40.dp)
                    .background(Shadow2)
                    .clickable { showAlertDialog1 = true }
            ) {
                Text(text = "取消")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .width(100.dp)
                    .height(40.dp)
                    .background(Shadow2)
                    .clickable { showAlertDialog2 = true }
            ) {
                Text(text = "送出")
            }
        }
    }
    if (showAlertDialog1) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                // 點擊 彈出視窗 外的區域觸發
                showAlertDialog1 = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "按下取消後，就無法認識並再",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )
                    Text(
                        text = "遇見這位朋友了喔",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )

                }
            },
            title = {
                Text(text = "", fontSize = 24.sp, style = MaterialTheme.typography.h6)
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = { showAlertDialog1 = false }
                        ) {
                            Text("取消", fontSize = 18.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = {
                                showAlertDialog1 = false
                                navController.navigate("home/feed")
                            }
                        ) {
                            Text("確定", fontSize = 18.sp)
                        }
                    }
                }
            }
        )
    }
    if (showAlertDialog2) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                showAlertDialog2 = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "如果對方也對你送出邀請的",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )
                    Text(
                        text = "話，你們就會變成好友喔!",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )
                }
            },
            title = {
                Text(text = "", fontSize = 24.sp)
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = { showAlertDialog2 = false }
                        ) {
                            Text("取消", fontSize = 18.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = {
                                addFriend( user.id.substring(0 .. 5), NewFriend[id].id.substring(0 .. 5) )
                                addFriend( NewFriend[id].id.substring(0 .. 5), user.id.substring(0 .. 5) )
                                showAlertDialog2 = false
                                navController.navigate("home/feed")
                            }
                        ) {
                            Text("確定", fontSize = 18.sp)
                        }
                    }
                }
            }
        )
    }
}

@Preview("default")
@Composable
fun MeetNewFriendPreview() {
    JetsnackTheme {
        //MeetNewFriend()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun showdialog1(modifier: Modifier, navController: NavHostController) {
    var showAlertDialog by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .width(340.dp)
            .padding(start = 16.dp, end = 16.dp)
    )
    {
        Box(modifier = modifier
            .size(50.dp)
            .clickable { showAlertDialog = true }) {
            Image(
                painter = painterResource(R.drawable.check),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
    if (showAlertDialog) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                // 點擊 彈出視窗 外的區域觸發
                showAlertDialog = false
            },
            text = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "是否確定更新",
                        fontSize = 24.sp,
                        color = Color(0xff000000),
                        style = MaterialTheme.typography.h6
                    )
                }
            },
            title = {
                Text(text = "", fontSize = 24.sp, style = MaterialTheme.typography.h6)
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = { showAlertDialog = false }
                        ) {
                            Text("取消", fontSize = 20.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Shadow3),
                            modifier = Modifier.width(100.dp),
                            onClick = {
                                showAlertDialog = false
                                navController.navigate("home/feed")
                            }
                        ) {
                            Text("確定", fontSize = 20.sp)
                        }
                    }
                }
            }
        )
    }

}

fun GetNewFriendid(): Int {
    return ((0..3).random() + (0..3).random() * (0..3).random() )% 4
}