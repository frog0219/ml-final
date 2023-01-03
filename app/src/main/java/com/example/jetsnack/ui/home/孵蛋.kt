package com.example.jetsnack.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Eggs
import com.example.jetsnack.database.Hatchs
import com.example.jetsnack.ui.theme.Shadow1
import com.example.jetsnack.ui.theme.Shadow2
import com.example.jetsnack.ui.theme.Shadow4

@Composable
fun HatchEggs(modifier: Modifier, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(360.dp)
                .background(Color(0xffffffff))
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
                    text = "孵蛋進度",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
                Box(modifier = modifier.size(25.dp)) {}
            }
        }
        Spacer(Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(360.dp)
                .height(740.dp)
                .background(Shadow1)
        ) {
            Spacer(modifier = modifier.height(16.dp))
            GetHatch(modifier = Modifier)
        }

    }

}

@Composable
private fun GetHatch(modifier: Modifier) {
    LazyColumn() {
        items((Hatchs.size + 1) / 2) { data ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(140.dp)
                ) {
                    if (data * 2 < Hatchs.size) {
                        Spacer(Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                        ) {
                            Image(
                                painter = painterResource(Eggs[Hatchs[data * 2].id].picture),
                                modifier = modifier.fillMaxSize(),
                                contentDescription = null
                            )

                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .width(60.dp)
                                .height(16.dp)
                                .background(Color(0x5f000000))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .width((60 * (Eggs[Hatchs[data * 2].id].time - Hatchs[data * 2].remain_time) / Eggs[Hatchs[data * 2].id].time).dp)
                                    .height(16.dp)
                                    .background(Shadow4)
                            ) {}
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(140.dp)
                ) {
                    if (data * 2 + 1 < Hatchs.size) {
                        Spacer(Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .height(90.dp)
                                .width(90.dp)
                        ) {
                            Image(
                                painter = painterResource(Eggs[Hatchs[data * 2 + 1].id].picture),
                                modifier = modifier.fillMaxSize(),
                                contentDescription = null
                            )

                        }
                        Spacer(Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .width(60.dp)
                                .height(16.dp)
                                .background(Color(0x5f000000))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .width((60 * (Eggs[Hatchs[data * 2 + 1].id].time - Hatchs[data * 2 + 1].remain_time) / Eggs[Hatchs[data * 2 + 1].id].time).dp)
                                    .height(16.dp)
                                    .background(Shadow4)
                            ) {}
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}