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
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.model.FocusRoom
import com.example.jetsnack.model.User
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import java.time.format.TextStyle
import kotlin.time.Duration.Companion.seconds

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MulModeHost(modifier: Modifier = Modifier, navController: NavHostController) {
    var showAlertDialog1 by remember { mutableStateOf(false) }
    var showAlertDialog2 by remember { mutableStateOf(false) }
    var showAlertDialog3 by remember { mutableStateOf(false) }
    var total by remember { mutableStateOf(focusRoom.sec) }
    var id1 by remember { mutableStateOf(focusRoom.id1) }
    var id2 by remember { mutableStateOf(focusRoom.id2) }
    var id3 by remember { mutableStateOf(focusRoom.id3) }
    var id4 by remember { mutableStateOf(focusRoom.id4) }
    var focus_state by remember { mutableStateOf<Hours>(FullHours(1, 30)) }
    val focus_hour = total / 3600
    val focus_min = (total % 3600) / 60
    var rest_state by remember { mutableStateOf<Hours>(FullHours(2, 0)) }
    var rest_min by remember { mutableStateOf(2) }
    var rest_sec by remember { mutableStateOf(0) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val selectindex by remember { mutableStateOf(Select_index) }
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .height(700.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Shadow0)
                .padding(10.dp)
        ) {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(1.seconds)
                    total = focusRoom.sec
                    id1 = focusRoom.id1
                    id2 = focusRoom.id2
                    id3 = focusRoom.id3
                    id4 = focusRoom.id4
                    if (focusRoom.start) navController.navigate("focusProcessMul/${focusRoom.sec}")
                }
            }
            /*Spacer(Modifier.height(48.dp))*/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                /*verticalAlignment = Alignment.CenterVertically*/
            ) {
                Box(
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp),
                ) {
                    Image(
                        painterResource(R.drawable.arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.popBackStack() }
                    )
                }
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                ) {
                    Image(
                        painterResource(R.drawable.key),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { showAlertDialog3 = true }
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(text = "Anderson 的房間", fontSize = 28.sp)
            Spacer(Modifier.height(70.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Box(
                        modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Shadow2)
                            .padding(10.dp)
                    ) {
                        Image(
                            painterResource(Dogs[2].picture),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(Modifier.width(30.dp))
                    Box(
                        modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Shadow2)
                            .padding(10.dp)
                    ) {
                        if (id2 != "") {
                            Image(
                                painterResource(R.drawable.animal_01),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))
                Row() {
                    Box(
                        modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Shadow2)
                            .padding(10.dp)
                    ) {
                        /*Image(
                            painterResource(R.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )*/
                    }
                    Spacer(Modifier.width(30.dp))
                    Box(
                        modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Shadow2)
                            .padding(10.dp)

                    ) {
                        /*Image(
                            painterResource(R.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )*/
                    }
                }
            }
            Spacer(Modifier.height(70.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        if (user.id == focusRoom.id1) showAlertDialog1 = true
                    }
                ) {
                    Text(text = "專注計時器", fontSize = 20.sp)
                    Divider(
                        modifier = Modifier.width(100.dp),
                        color = Color.Gray,
                        thickness = 1.dp,
                    )
                    Text(
                        text = String.format("  %02d:%02d ▼", focus_hour, focus_min),
                        fontSize = 24.sp
                    )
                }
                /*Spacer(Modifier.width(40.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { showAlertDialog2 = true }
                ) {
                    Text(text = "休息計時器", fontSize = 20.sp)
                    Text(text = String.format("%02d:%02d", rest_min, rest_sec), fontSize = 24.sp)
                }*/
            }
            Spacer(Modifier.height(60.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        Shadow4
                    )
                    .clickable {
                        if (user.id == focusRoom.id1) multiFocusStart(focusRoom.roomId)
                    }
            ) {
                if (user.id == focusRoom.id1) Text(
                    text = "多人開始",
                    color = Color.White,
                    fontSize = 20.sp
                )
                else Text(text = "只有房主可以開始", color = Color.White, fontSize = 16.sp)

            }
        }
    }
    //彈出視窗介面1
    if (showAlertDialog1) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(250.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                showAlertDialog1 = false
            },
            text = {
            },
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "專注計時器",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )
                    Spacer(Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = Color.DarkGray,
                        thickness = 1.dp,
                    )
                }
            },
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row( //滾輪
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HoursNumberPicker(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(vertical = 16.dp), leadingZero = true,
                            value = focus_state,
                            onValueChange = {
                                focus_state = it
                            },
                            minutesRange = IntProgression.fromClosedRange(0, 55, 1),
                            hoursDivider = {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Center,
                                    text = "h"
                                )
                            },
                            minutesDivider = {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Center,
                                    text = "m"
                                )
                            }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
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
                                    total = focus_state.hours * 3600 + focus_state.minutes * 60
                                    timeUpdate(total, focusRoom.roomId)
                                    showAlertDialog1 = false
                                }
                            ) {
                                Text("確定", fontSize = 18.sp)
                            }
                        }
                    }
                }

            }
        )
    }
    //2
    if (showAlertDialog2) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(250.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                showAlertDialog2 = false
            },
            text = {
            },
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "休息計時器",
                        fontSize = 18.sp,
                        color = Color(0xff000000),
                    )
                    Spacer(Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = Color.DarkGray,
                        thickness = 1.dp,
                    )
                }
            },
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row( //滾輪
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        HoursNumberPicker(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(vertical = 16.dp), leadingZero = true,
                            value = rest_state,
                            onValueChange = {
                                rest_state = it
                            },
                            minutesRange = IntProgression.fromClosedRange(0, 59, 1),
                            hoursDivider = {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Center,
                                    text = "m"
                                )
                            },
                            minutesDivider = {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Center,
                                    text = "s"
                                )
                            }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
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
                                    showAlertDialog2 = false
                                    rest_min = rest_state.hours
                                    rest_sec = rest_state.minutes
                                }
                            ) {
                                Text("確定", fontSize = 18.sp)
                            }
                        }
                    }
                }

            }
        )
    }
    //3
    if (showAlertDialog3) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(250.dp)
                .height(120.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                showAlertDialog3 = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "房間邀請碼",
                        fontSize = 20.sp,
                        color = Color(0xff000000),
                    )
                    Spacer(Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = Color.DarkGray,
                        thickness = 1.dp,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = focusRoom.roomId,
                        fontSize = 23.sp,
                        color = Color(0xff000000), letterSpacing = 3.sp
                    )
                    Spacer(Modifier.height(20.dp))
                }
            },
            title = {

            },
            buttons = {

            }
        )
    }
}

fun gethash(id: String): String {
    return id.substring(0, 6)
}

fun multiFocusBuild(id1: String) {
    val id = gethash(user.id)
    val ref = FirebaseDatabase.getInstance().getReference("/focusRooms/$id")
    focusRoom = FocusRoom(id1, "", "", "", id, false, 900)
    MutipleFocus.START = false
    MutipleFocus.Isfull = false
    MutipleFocus.nowTime = 900
    ref.setValue(focusRoom)
    val ref1 = FirebaseDatabase.getInstance().getReference("/focusRooms")
    ref1.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            // Get Post object and use the values to update the UI
            p0.children.forEach {
                val room = it.getValue(FocusRoom::class.java)
                if (room != null) {
                    if (room.roomId == id) {
                        focusRoom = room
                    }
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    })
}
fun enterFocusRoom(invitedId: String, RoomId: String):Boolean {
    val ref = FirebaseDatabase.getInstance().getReference("/focusRooms")
    var flag = false
    ref.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            // Get Post object and use the values to update the UI
            p0.children.forEach {
                val room = it.getValue(FocusRoom::class.java)
                if (room != null) {
                    if (room.roomId == RoomId) {
                        flag = true
                        if (room.id2 == "") {
                            val ref2 = FirebaseDatabase.getInstance()
                                .getReference("/focusRooms/$RoomId/id2")
                            ref2.setValue(invitedId)
                        } else if (room.id3 == "") {
                            val ref2 = FirebaseDatabase.getInstance()
                                .getReference("/focusRooms/$RoomId/id3")
                            ref2.setValue(invitedId)
                        } else if (room.id4 == "") {
                            val ref2 = FirebaseDatabase.getInstance()
                                .getReference("/focusRooms/$RoomId/id4")
                            ref2.setValue(invitedId)
                        } else {
                            MutipleFocus.Isfull = true
                        }
                        focusRoom = room
                    }
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    })
    return flag
}

fun multiFocusStart(RoomId: String) {
    val ref = FirebaseDatabase.getInstance().getReference("/focusRooms/$RoomId/start")
    ref.setValue(true)
}

fun timeUpdate(sec: Int, RoomId: String) {
    val ref = FirebaseDatabase.getInstance().getReference("/focusRooms/$RoomId/sec")
    ref.setValue(sec)
}

@Preview("defa", showBackground = true)
@Composable
fun MulModeHostPreview() {
    JetsnackTheme {
        //MulModeHost()
    }
}
