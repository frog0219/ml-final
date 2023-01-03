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


import android.util.Log
import android.widget.NumberPicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.jetsnack.R
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.database.MutipleFocus
import com.example.jetsnack.database.Select_index
import com.example.jetsnack.database.user
import com.example.jetsnack.repository.AuthorRepo
import com.example.jetsnack.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.intellij.lang.annotations.JdkConstants
import kotlin.math.abs

sealed interface Hours {
    val hours: Int
    val minutes: Int
}

data class FullHours(
    override val hours: Int,
    override val minutes: Int,
) : Hours

data class AMPMHours(
    override val hours: Int,
    override val minutes: Int,
    val dayTime: DayTime
) : Hours {
    enum class DayTime {
        AM,
        PM;
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavHostController) {
    var showAlertDialog1 by remember { mutableStateOf(false) }
    var showAlertDialog2 by remember { mutableStateOf(false) }
    var showAlertDialog3 by remember { mutableStateOf(false) }
    var showAlertDialog4 by remember { mutableStateOf(false) }
    var showAlertDialog5 by remember { mutableStateOf(false) }
    var focus_state by remember { mutableStateOf<Hours>(FullHours(1, 30)) }
    var focus_hour by remember { mutableStateOf(1) }
    var focus_min by remember { mutableStateOf(30) }
    var rest_state by remember { mutableStateOf<Hours>(FullHours(2, 0)) }
    var rest_min by remember { mutableStateOf(2) }
    var rest_sec by remember { mutableStateOf(0) }
    var total by remember { mutableStateOf(0) }
    val selectindex by remember { mutableStateOf(Select_index) }
    var invitation by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            /*.padding(24.dp)*/
            .height(760.dp)
            .background(Shadow0)
        /*.wrapContentSize()
        .padding(24.dp)*/
    ) {

        Spacer(Modifier.height(130.dp))
        Box(
            modifier = Modifier
                .size(370.dp)
                .clip(CircleShape)
                .background(Shadow1)
                /*.clickable { navController.navigate("SelectDog") }*/
                .padding(0.dp)
                .padding(30.dp)

        ) {
            Image(
                painterResource(R.drawable.cover_dog),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(Modifier.height(40.dp))
        Text(text = "Welcome to \"Who are you, doggy?\"", fontSize = 24.sp)
        Spacer(Modifier.height(40.dp))
        Text(text = "You could press the button below to ", fontSize = 20.sp, color = Color.Gray)
        Text(text = "explore our app!", fontSize = 20.sp, color = Color.Gray)
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
                                    showAlertDialog1 = false
                                    focus_hour = focus_state.hours
                                    focus_min = focus_state.minutes
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
                .width(300.dp)
                .height(200.dp),
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
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "開始多人模式",
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
                    /*TextField(
                        value = invitation,
                        onValueChange = {invitation = it },
                        //placeholder = { Text(text = "新增描述", color = Color(0x50000000)) },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .width(220.dp)
                            .height(50.dp)
                            .background(Shadow0),
                        //shape = RoundedCornerShape(10.dp),
                    )*/
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
                                    multiFocusBuild(user.id)
                                    showAlertDialog3 = false
                                    navController.navigate("mulModeHost")
                                }
                            ) {
                                Text("創建房間", fontSize = 18.sp)
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
                                    showAlertDialog3 = false
                                    showAlertDialog4 = true
                                }
                            ) {
                                Text("加入房間", fontSize = 18.sp)
                            }
                        }
                    }
                }

            }
        )
    }
    //4
    if (showAlertDialog4) {
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
                showAlertDialog4 = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "輸入邀請碼",
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
                    TextField(
                        value = invitation,
                        onValueChange = { invitation = it },
                        //placeholder = { Text(text = "新增描述", color = Color(0x50000000)) },
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .width(220.dp)
                            .height(50.dp)
                            .background(Shadow0),
                        shape = RectangleShape,
                        colors = TextFieldDefaults.textFieldColors(Shadow0)
                    )
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
                                modifier = Modifier.width(100.dp),
                                onClick = {
                                    showAlertDialog4 = false
                                    //navController.navigate("mulModeHost")
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
                                modifier = Modifier.width(100.dp),
                                onClick = {
                                    var correct = enterFocusRoom(user.id, invitation)
                                    showAlertDialog4 = false
                                    //if (correct) {
                                    navController.navigate("mulModeHost")
                                    //} else showAlertDialog5 = true
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
    //5
    if (showAlertDialog5) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .width(300.dp)
                .height(150.dp),
            backgroundColor = Color(0xffffffff),
            contentColor = Color(0xff000000),
            onDismissRequest = {
                showAlertDialog5 = false
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        text = "邀請碼錯誤",
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
                                onClick = {
                                    showAlertDialog5 = false
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

@Composable
private fun HoursNumberPicker3() {
    var focus_state by remember { mutableStateOf<Hours>(FullHours(1, 30)) }

    HoursNumberPicker(
        modifier = Modifier
            .width(200.dp)
            .padding(vertical = 16.dp), leadingZero = true,
        value = focus_state,
        onValueChange = {
            focus_state = it
        },
        minutesRange = IntProgression.fromClosedRange(0, 55, 5),
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

@Composable
fun HoursNumberPicker(
    modifier: Modifier = Modifier,
    value: Hours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int> = when (value) {
        is FullHours -> (0..23)
        is AMPMHours -> (1..12)
    },
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = Shadow4,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    when (value) {
        is FullHours ->
            FullHoursNumberPicker(
                modifier = modifier,
                value = value,
                leadingZero = leadingZero,
                hoursRange = hoursRange,
                minutesRange = minutesRange,
                hoursDivider = hoursDivider,
                minutesDivider = minutesDivider,
                onValueChange = onValueChange,
                dividersColor = dividersColor,
                textStyle = textStyle,
            )
        is AMPMHours ->
            AMPMHoursNumberPicker(
                modifier = modifier,
                value = value,
                leadingZero = leadingZero,
                hoursRange = hoursRange,
                minutesRange = minutesRange,
                hoursDivider = hoursDivider,
                minutesDivider = minutesDivider,
                onValueChange = onValueChange,
                dividersColor = dividersColor,
                textStyle = textStyle,
            )
    }
}

@Composable
fun FullHoursNumberPicker(
    modifier: Modifier = Modifier,
    value: FullHours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int>,
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = Shadow4,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.hours,
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = Shadow4,
            textStyle = textStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = Shadow4,
            textStyle = textStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()
    }
}

@Composable
fun AMPMHoursNumberPicker(
    modifier: Modifier = Modifier,
    value: AMPMHours,
    leadingZero: Boolean = true,
    hoursRange: Iterable<Int>,
    minutesRange: Iterable<Int> = (0..59),
    hoursDivider: (@Composable () -> Unit)? = null,
    minutesDivider: (@Composable () -> Unit)? = null,
    onValueChange: (Hours) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            modifier = Modifier.weight(1f),
            value = value.hours,
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()

        NumberPicker(
            value = when (value.dayTime) {
                AMPMHours.DayTime.AM -> 0
                else -> 1
            },
            label = {
                when (it) {
                    0 -> "AM"
                    else -> "PM"
                }
            },
            onValueChange = {
                onValueChange(
                    value.copy(
                        dayTime = when (it) {
                            0 -> AMPMHours.DayTime.AM
                            else -> AMPMHours.DayTime.PM
                        }
                    )
                )
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = (0..1)
        )
    }
}

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    label: (Int) -> String = {
        it.toString()
    },
    value: Int,
    onValueChange: (Int) -> Unit,
    dividersColor: Color = Shadow4,
    range: Iterable<Int>,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    ListItemPicker(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        dividersColor = dividersColor,
        list = range.toList(),
        textStyle = textStyle
    )
}

