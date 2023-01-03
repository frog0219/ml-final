package com.example.jetsnack.ui.home

import com.example.jetsnack.model.*

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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.repository.AuthorRepo
import com.example.jetsnack.ui.components.*
import com.example.jetsnack.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun DogSelect(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    val dogsscollection = Dogs
    Doggy(
        dogsscollection,
        modifier,
        navController = navController
    )
}

@Composable
fun Doggy(
    dogscollection: List<Dog>,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(760.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xfff3edf7))
                .clip(RoundedCornerShape(24.dp))
                .width(360.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color(0xffffffff))
            ) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 28.dp))
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Box(modifier = modifier.size(25.dp)) {
                        Image(
                            painter = painterResource(R.drawable.navigate_before),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { navController.popBackStack() }
                        )
                    }
                    Text(
                        text = "寵物選擇",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                    )
                    Box(
                        modifier = modifier
                            .width(25.dp)
                            .height(25.dp)
                    ) {

                    }
                }
                Spacer(Modifier.height(12.dp))
            }
            DogSelect(dogscollection, modifier, navController = navController)


        }
    }
}

@Composable
private fun DogSelect(
    dogscollection: List<Dog>,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    var selectindex by remember { mutableStateOf(Select_index) }
    Select_index = selectindex
    LazyColumn(modifier = Modifier.background(Color(0x03f3edf7))) {

        items(8) { data ->
            Spacer(Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(180.dp)
                ) {
                    Spacer(Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clickable { navController.navigate("PetDetail/" + data * 2) }
                    ) {
                        Image(
                            painter = painterResource(dogscollection[data * 2].picture),
                            modifier = modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }
                    Spacer(Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                if (dogscollection[data * 2].own) selectindex = data * 2
                                user.equipDog = selectindex
                                GlobalScope.launch(Dispatchers.Main) {
                                    UpdateUser()
                                }
                            }
                            .background(
                                if (dogscollection[data * 2].own) {
                                    if (data * 2 == selectindex) Color(0x30000000) else Color(
                                        0x8F735bf2
                                    )
                                } else Color(0xff000000)
                            )
                            .size(width = 108.dp, 32.dp)
                    ) {
                        Text(
                            text = if (dogscollection[data * 2].own) {
                                if (data * 2 == selectindex) "Equipped" else "Select"
                            } else "locked",
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = if (data * 2 == selectindex) Color(0xff000000)
                            else Color(0xffffffff)
                        )
                    }

                }
                Spacer(Modifier.width(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(180.dp)
                ) {
                    Spacer(Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clickable { navController.navigate("PetDetail/" + (data * 2 + 1)) }
                    ) {
                        Image(
                            painter = painterResource(dogscollection[data * 2 + 1].picture),
                            modifier = modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }
                    Spacer(Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                if (dogscollection[data * 2 + 1].own) selectindex = data * 2 + 1
                                user.equipDog = selectindex
                                GlobalScope.launch(Dispatchers.Main) {
                                    UpdateUser()
                                }
                            }
                            .background(
                                if (dogscollection[data * 2 + 1].own) {
                                    if (data * 2 + 1 == selectindex) Color(0x30000000) else Color(
                                        0x8F735bf2
                                    )
                                } else Color(0xff000000)
                            )
                            .size(width = 108.dp, 32.dp)
                    ) {
                        Text(
                            text = if (dogscollection[data * 2 + 1].own) {
                                if (data * 2 + 1 == selectindex) "Equipped" else "Select"
                            } else "locked",
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = if (data * 2 + 1 == selectindex) Color(0xff000000)
                            else Color(0xffffffff)
                        )
                    }

                }

            }
        }

        item { Spacer(Modifier.height(16.dp)) }
    }
}


