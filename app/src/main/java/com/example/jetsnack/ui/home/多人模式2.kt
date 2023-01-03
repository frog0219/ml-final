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
import androidx.compose.runtime.Composable
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
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*

@Composable
fun MulModeGuest(modifier: Modifier = Modifier, navController: NavHostController) {
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
            /**/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                            .clickable { }
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
            Text(text = "Dora 的房間", fontSize = 28.sp)
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
                            painterResource(R.drawable.dogg),
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
                        Image(
                            painterResource(R.drawable.cat1),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
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
                        Image(
                            painterResource(R.drawable.plus),
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
                        Image(
                            painterResource(R.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
            Spacer(Modifier.height(70.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "專注計時器", fontSize = 20.sp)
                    Divider(
                        modifier = Modifier.padding(horizontal = 125.dp, vertical = 10.dp),
                        color = Color.Gray,
                        thickness = 1.dp,
                    )
                    Text(text = "05:00", fontSize = 24.sp)
                }
            }
            Spacer(Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp),
                ) {
                    Image(
                        painterResource(R.drawable.alarm),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(Modifier.width(5.dp))
                Text(text = "只有房主可以調整時間")
            }
            Spacer(Modifier.height(32.dp))
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
            ) {
                Text(text = "等待房主開始", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

@Preview("de", showBackground = true)
@Composable
fun MulModeGuestPreview() {
    JetsnackTheme {
        //MulModeGuest()
    }
}
