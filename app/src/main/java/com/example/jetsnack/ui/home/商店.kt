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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.model.SnackCollection
import com.example.jetsnack.model.SnackRepo
import com.example.jetsnack.ui.components.*
import com.example.jetsnack.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun Shop(
    modifier: Modifier = Modifier
) {
    val eggscollection = Eggs
    Shop(
        eggscollection,
        modifier
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Shop(eggscollection: List<Egg>, modifier: Modifier = Modifier) {
    val money = remember { mutableStateOf(user.money) }
    val Money_not_enough = remember { mutableStateOf(false) }
    val Cheeck_buy = remember { mutableStateOf(false) }
    val buy_money = remember { mutableStateOf(0) }
    var id = remember { mutableStateOf(0) }
    val buy_egg_index = remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
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
                Row(modifier = modifier.width(120.dp)) {

                }
                Text(
                    text = "商店",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.width(120.dp)
                ) {
                    Text(
                        text = money.value.toString(),
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,

                        )
                    Spacer(
                        Modifier.width(5.dp)
                    )
                    Image(
                        painterResource(R.drawable.coin),
                        contentDescription = null,
                        modifier = modifier.size(20.dp)
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
        }
        LazyColumn {
            item { Spacer(Modifier.height(8.dp)) }
            items(4) { data ->
                //Spacer(Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(eggscollection[data * 2].color))
                            .width(180.dp)
                            .clickable {
                                if (money.value - eggscollection[data * 2].price >= 0) {
                                    Cheeck_buy.value = true
                                    buy_money.value = eggscollection[data * 2].price
                                    id.value = data *2
                                    buy_egg_index.value = data * 2
                                } else Money_not_enough.value = true

                            }
                    ) {
                        Spacer(Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                        ) {
                            Image(
                                painter = painterResource(eggscollection[data * 2].picture),
                                modifier = modifier.fillMaxSize(),
                                contentDescription = null
                            )

                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            fontSize = 20.sp,
                            text = eggscollection[data * 2].name + "專注蛋",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Image(
                                painterResource(R.drawable.coin),
                                contentDescription = null,
                                modifier = modifier.size(18.dp)
                            )
                            Spacer(
                                Modifier.width(10.dp)
                            )
                            Text(
                                fontSize = 18.sp,
                                text = "${eggscollection[data * 2].price}",
                                textAlign = TextAlign.Center,
                            )
                            Spacer(
                                Modifier.width(10.dp)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                    }
                    Spacer(Modifier.width(20.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(eggscollection[data * 2 + 1].color))
                            .width(180.dp)
                            .clickable {
                                if (money.value - eggscollection[data * 2 + 1].price >= 0) {
                                    Cheeck_buy.value = true
                                    buy_money.value = eggscollection[data * 2 + 1].price
                                    id.value = data*2+1
                                    buy_egg_index.value = data * 2 + 1
                                } else Money_not_enough.value = true
                            }

                    ) {
                        Spacer(Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                        ) {
                            Image(
                                painter = painterResource(eggscollection[data * 2 + 1].picture),
                                modifier = modifier.fillMaxSize(),
                                contentDescription = null
                            )

                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            fontSize = 20.sp,
                            text = eggscollection[data * 2 + 1].name + "專注蛋",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Image(
                                painterResource(R.drawable.coin),
                                contentDescription = null,
                                modifier = modifier.size(20.dp)
                            )
                            Spacer(
                                Modifier.width(10.dp)
                            )
                            Text(
                                fontSize = 18.sp,
                                text = "${eggscollection[data * 2 + 1].price}",
                                textAlign = TextAlign.Center,
                            )
                            Spacer(
                                Modifier.width(10.dp)
                            )

                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }

    if (Money_not_enough.value) {
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
                Money_not_enough.value = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "剩餘金額不足",
                        fontSize = 20.sp,
                        color = Color(0xff000000),
                    )

                }
            },
            title = {

            },
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(20.dp))
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
                                modifier = Modifier.width(120.dp),
                                onClick = {
                                    Money_not_enough.value = false
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
    if (Cheeck_buy.value) {
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
                Cheeck_buy.value = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "確認購買",
                        fontSize = 20.sp,
                        color = Color(0xff000000),
                    )
                    Text(
                        text = "\"${eggscollection[buy_egg_index.value].name} 專注蛋\"",
                        fontSize = 20.sp,
                        color = Shadow4,
                    )

                }
            },
            title = {

            },
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(20.dp))
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
                                modifier = Modifier.width(120.dp),
                                onClick = {
                                    Cheeck_buy.value = false
                                }
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
                                modifier = Modifier.width(120.dp),
                                onClick = {
                                    money.value -= buy_money.value
                                    Cheeck_buy.value = false
                                    Hatchs.add( 0 , Hatch(remain_time =  Eggs[id.value].time ,id = id.value))
                                    user.money = money.value
                                    GlobalScope.launch(Dispatchers.Main) {
                                        UpdateUser()
                                    }
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

}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun EggsPreview() {
    JetsnackTheme {
        Shop()
    }
}
