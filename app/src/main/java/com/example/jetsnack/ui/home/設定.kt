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

import android.app.Notification
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.UpdateUser
import com.example.jetsnack.database.user
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun Settings(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(760.dp)
            .padding(20.dp)
    ) {
        Spacer(Modifier.height(48.dp))
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
                        .clickable {
                            navController.popBackStack()
                            GlobalScope.launch(Dispatchers.Main) {
                                UpdateUser()
                            }
                        }
                )
            }
            Text(text = "設定", fontSize = 24.sp)
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
            ) {
            }
        }
        Spacer(Modifier.height(40.dp))
        val notificationCheckedState = remember { mutableStateOf(user.notification) }
        val friendCheckedState = remember { mutableStateOf(user.new_friend) }
        val moonCheckedState = remember { mutableStateOf(user.moon) }
        SettingsRowSwitch(
            image = R.drawable.friend,
            text1 = "通知",
            text2 = "提醒你的待辦事項和新訊息",
            checkedstate = notificationCheckedState
        )
        Spacer(Modifier.height(30.dp))
        SettingsRowSwitch(
            image = R.drawable.friend,
            text1 = "遇見新朋友",
            text2 = "在專注結束後有機會遇到新朋友",
            checkedstate = friendCheckedState
        )
        Spacer(Modifier.height(30.dp))
        SettingsRowSwitch(
            image = R.drawable.moon,
            text1 = "勿擾模式",
            text2 = "在專注期間隔絕外界的誘惑",
            checkedstate = moonCheckedState
        )
        Spacer(Modifier.height(30.dp))
        SettingsRowArrow(
            image = R.drawable.report,
            text1 = "問題回報",
            text2 = "回報您遇到的問題",
            navController = navController
        )
        Spacer(Modifier.height(30.dp))
        SettingsRowArrow(
            image = R.drawable.search_new,
            text1 = "常見問題",
            text2 = "看看別人遇到了什麼問題",
            navController = navController
        )
    }
}

@Preview("1226", showBackground = true)
@Composable
fun SettingsPreview() {
    JetsnackTheme {
        //Settings()
    }
}

@Composable
fun SettingsRowSwitch(
    image: Int,
    text1: String,
    text2: String,
    checkedstate: MutableState<Boolean>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
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
                Text(text = text1, fontSize = 20.sp)
                Spacer(Modifier.height(4.dp))
                Text(text = text2, fontSize = 16.sp, color = Color.Gray)
            }
        }
        Switch(
            checked = checkedstate.value,
            onCheckedChange = { checkedstate.value = it },
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.DarkGray
            )

        )
    }
    if (text1 == "通知") user.notification = checkedstate.value
    else if (text1 == "遇見新朋友") user.new_friend = checkedstate.value
    else if (text1 == "勿擾模式") user.moon = checkedstate.value

}

@Composable
fun SettingsRowArrow(image: Int, text1: String, text2: String, navController: NavHostController) {
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
                Text(text = text1, fontSize = 20.sp)
                Spacer(Modifier.height(4.dp))
                Text(text = text2, fontSize = 16.sp, color = Color.Gray)
            }
        }
        Row() {
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clickable {
                        navController.navigate(
                            when (text1) {
                                "問題回報" -> "Report"
                                "常見問題" -> "CommonQuestion"
                                else -> "CommonQuestion"
                            }
                        )

                    },
            ) {
                Image(
                    painterResource(R.drawable.arrow2),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(Modifier.width(10.dp))
        }

    }

}
