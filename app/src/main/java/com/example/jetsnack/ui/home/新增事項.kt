package com.example.jetsnack.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Calenders
import com.example.jetsnack.ui.theme.Rose10
import com.example.jetsnack.ui.theme.Shadow3

@Composable
fun AddCalender(
    modifier: Modifier, navController: NavHostController,
    year: Int,
    month: Int,
    day: Int,
    week: String,
    time: Int
) {
    var header by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val week_abi = when(week){
        "Monday" -> "MON"
        "Tuesday" -> "TUE"
        "Wednesday" -> "WED"
        "Thursday" -> "THU"
        "Friday" -> "FRI"
        "Saturday" -> "SAT"
        "Sunday" -> "SUN"
        else -> "FUCK"
    }
    val next_hour = time + 1
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(380.dp)
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
                    text = "新增事項",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.subtitle1
                )
                Box(modifier = modifier.size(25.dp)) {}

            }
            Spacer(modifier = modifier.height(24.dp))
            TextField(
                value = header,
                onValueChange = { header = it },
                placeholder = { Text(text = "新增標題", color = Color(0x50000000)) },
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(20.dp)
                    .width(380.dp)
                    .background(Color(0x30f3edf7)),
                shape = RoundedCornerShape(10.dp),
            )
            //Spacer(modifier = modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .height(48.dp)
                    .width(340.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff3edf7))
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "開始時間", color = Color(0x80000000),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
                Text(
                    text = " $month/$day($week_abi) $time:00", color = Color(0x80000000),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .height(48.dp)
                    .width(340.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xfff3edf7))
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "結束時間", color = Color(0x80000000),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
                Text(
                    text = " $month/$day($week_abi) $next_hour:00", color = Color(0x80000000),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            TextField(
                value = content,
                onValueChange = { content = it },
                placeholder = { Text(text = "新增描述", color = Color(0x50000000)) },
                textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(20.dp)
                    .width(380.dp)
                    .height(300.dp)
                    .background(Color(0x30f3edf7)),
                shape = RoundedCornerShape(10.dp),
            )
            CheckCalender(modifier = Modifier, navController = navController , next_hour = next_hour ,header = header)
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CheckCalender(modifier: Modifier, navController: NavHostController ,next_hour:Int ,header:String) {
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
                                Calenders[next_hour] = header
                                showAlertDialog = false
                                navController.popBackStack()
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