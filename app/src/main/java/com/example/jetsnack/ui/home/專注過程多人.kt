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
import com.example.jetsnack.database.Select_index
import com.example.jetsnack.database.focusRoom
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun FocusProcessMul(modifier: Modifier = Modifier, navController: NavHostController, total: Int) {
    val selectindex by remember { mutableStateOf(Select_index) }
    var ticks by remember { mutableStateOf(total) }
    var ticks2 by remember { mutableStateOf(0) }
    var img by remember { mutableStateOf(R.drawable.pause) }
    var pause by remember { mutableStateOf(false) }

    if (ticks <= 0 && ticks >=-2) {
        navController.navigate("completeFocusMul")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.Start
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
                            .clickable { navController.navigate(HomeSections.FEED.route) }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Shadow2)
                        .padding(0.dp)
                        .padding(10.dp)
                ) {
                    Image(
                        painterResource(Dogs[2].picture),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                if (focusRoom.id2 != "") {
                    Spacer(Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Shadow2)
                            .padding(0.dp)
                            .padding(10.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.animal_01),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )


                    }
                }
            }
            if (ticks2 == 0) {
                Text(text = "????????????", fontSize = 20.sp, color = Color.DarkGray)
            } else Text(
                text = String.format(
                    "????????????(-%02d:%02d)",
                    (ticks2 / 60) % 60,
                    ticks2 % 60
                ), fontSize = 20.sp, color = Color.DarkGray
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = String.format(
                    "%02d:%02d:%02d",
                    ticks / 3600,
                    (ticks / 60) % 60,
                    ticks % 60
                ), fontSize = 24.sp, color = Color.DarkGray
            )
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp),
            ) {
                Image(
                    painterResource(R.drawable.walkingdog),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(Modifier.height(25.dp))

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clickable { pause = !pause }
            ) {
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(0.01.seconds)
                        if (!pause) ticks--
                        if (pause) ticks2++
                    }
                }
                if (!pause) img = R.drawable.pause
                else img = R.drawable.play
                Image(
                    painterResource(img),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview("d")
@Composable
fun FocusProcessMulPreview() {
    JetsnackTheme {
        //FocusProcessMul()
    }
}
