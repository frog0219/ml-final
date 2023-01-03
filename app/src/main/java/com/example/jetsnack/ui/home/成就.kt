package com.example.jetsnack.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.model.*
import com.example.jetsnack.ui.theme.JetsnackTheme
import com.example.jetsnack.ui.theme.Rose11

@Composable
fun Achieve(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    ShowAchieve(
        modifier,
        navController = navController
    )
}

@Composable
fun ShowAchieve(
    modifier: Modifier,
    navController: NavHostController
) {
    var pageindex by remember { mutableStateOf(1) }
    var numindex by remember { mutableStateOf(4) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xfff3edf7))
                .width(360.dp)
                .height(760.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color(0xffffffff))
            ) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 14.dp))
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = modifier.size(25.dp)) {
                            Image(
                                painter = painterResource(R.drawable.navigate_before),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {navController.popBackStack()}
                            )
                        }
                        Spacer(modifier = Modifier.width(45.dp))
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = modifier.size(16.dp)) {
                            if (pageindex >= 1) {
                                Image(
                                    painter = painterResource(R.drawable.navigate_before),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            pageindex -= 1
                                            numindex = if (pageindex == 1) 4 else 3
                                        }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(
                            text = "成就",
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Box(modifier = modifier.size(16.dp)) {
                            if (pageindex <= 1) {
                                Image(
                                    painter = painterResource(R.drawable.navigate_after),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            pageindex += 1
                                            numindex = if (pageindex == 1) 4 else 3
                                        }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = modifier
                            .width(70.dp)
                            .height(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.tag),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {}
                        )
                        if (pageindex != 1) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .height(60.dp)
                            ) {
                                Spacer(modifier = Modifier.height(if (pageindex == 0)13.dp else 28.dp))
                                Text(
                                    text = if (pageindex == 0) "節" else "特",
                                    fontSize = 12.sp,
                                    style = MaterialTheme.typography.button,
                                    color = Color(0xffffffff)
                                )
                                Text(
                                    text = if (pageindex == 0) "日" else "",
                                    fontSize = 12.sp,
                                    style = MaterialTheme.typography.button,
                                    color = Color(0xffffffff)
                                )
                            }

                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
            }
            AchieveSelect(modifier, pageindex, numindex)

        }
    }

}

@Composable
private fun AchieveSelect(
    modifier: Modifier,
    pageindex: Int,
    numindex: Int,
) {
    LazyColumn(modifier = Modifier.background(Color(0x03f3edf7))) {

        items(numindex) { data ->
            Spacer(Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.width(4.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(85.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(85.dp)
                            .clickable { }
                            .background(
                                if (ListOfAchieve[pageindex][data * 3].own) Color(0x90FF6434)
                                else Color(0x30000000)
                            )
                            .padding(top = 6.dp, bottom = 6.dp)

                    ) {
                        Text(
                            text = ListOfAchieve[pageindex][data * 3].name,
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color(0xffffffff)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { }
                            .background(Color(0xffe6d8f2))
                            .padding(6.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                if (ListOfAchieve[pageindex][data * 3].own)
                                    ListOfAchieve[pageindex][data * 3].picture_own
                                else ListOfAchieve[pageindex][data * 3].picture_lost
                            ),
                            modifier = modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(85.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(85.dp)
                            .clickable { }
                            .background(
                                if (ListOfAchieve[pageindex][data * 3 + 1].own) Color(0x90FF6434)
                                else Color(0x30000000)
                            )
                            .padding(top = 6.dp, bottom = 6.dp)

                    ) {
                        Text(
                            text = ListOfAchieve[pageindex][data * 3 + 1].name,
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color(0xffffffff)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { }
                            .background(Color(0xffe6d8f2))
                            .padding(6.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                if (ListOfAchieve[pageindex][data * 3 + 1].own)
                                    ListOfAchieve[pageindex][data * 3 + 1].picture_own
                                else ListOfAchieve[pageindex][data * 3 + 1].picture_lost
                            ),
                            modifier = modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(85.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(85.dp)
                            .clickable { }
                            .background(
                                if (ListOfAchieve[pageindex][data * 3 + 2].own) Color(0x90FF6434)
                                else Color(0x30000000)
                            )
                            .padding(top = 6.dp, bottom = 6.dp)

                    ) {
                        Text(
                            text = ListOfAchieve[pageindex][data * 3 + 2].name,
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color(0xffffffff)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { }
                            .background(Color(0xffe6d8f2))
                            .padding(6.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                if (ListOfAchieve[pageindex][data * 3 + 2].own)
                                    ListOfAchieve[pageindex][data * 3 + 2].picture_own
                                else ListOfAchieve[pageindex][data * 3 + 2].picture_lost
                            ),
                            modifier = modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }

                }
                Spacer(Modifier.width(4.dp))
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
