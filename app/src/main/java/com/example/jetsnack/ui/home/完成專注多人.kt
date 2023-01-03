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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.database.Select_index
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*

@Composable
fun CompleteFocusMul(modifier: Modifier = Modifier, navController: NavHostController) {
    val selectindex by remember { mutableStateOf(Select_index) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            Text(text = "Congratulations!", fontSize = 40.sp)
            Spacer(Modifier.height(24.dp))
            Text(text = "You and your friends achieved", fontSize = 24.sp)
            Text(text = "your goal!", fontSize = 24.sp)
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
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
                        painterResource(Dogs[selectindex].picture),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Column(
                modifier = Modifier.height(250.dp)
            ) {
                Image(
                    painterResource(R.drawable.manwithdog),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate(HomeSections.FEED.route) },
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = FunctionalGreen),
            ) {
                Text("獲得 30 ", color = Ocean11, fontSize = 20.sp)
                Column(modifier = Modifier.height(24.dp)) {
                    Image(
                        painterResource(R.drawable.coin),
                        contentDescription = null,
                    )
                }

            }
            Spacer(Modifier.height(15.dp))
            Text(text = "你可以獲得額外20%的獎勵")
        }
    }
}

@Preview("defaut")
@Composable
fun CompleteFocusMulPreview() {
    JetsnackTheme {
        //CompleteFocusMul()
    }
}
