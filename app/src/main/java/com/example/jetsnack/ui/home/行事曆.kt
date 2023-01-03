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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.jetsnack.database.Calenders
import com.example.jetsnack.ui.theme.*
import java.util.*
import kotlin.math.abs

@Composable
fun Calendar(
    modifier: Modifier,
    navController: NavHostController,
    year: Int?,
    month: Int?,
    day: Int?,
    week: String?
) {
    val Nyear = remember { mutableStateOf(year) }
    val Nmonth = remember { mutableStateOf(month) }
    val Nday = remember { mutableStateOf(day) }
    val Nweek = remember { mutableStateOf(week) }
    val numberOfMonth: Array<Int> = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val month_string = when (Nmonth.value) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "fuck"
    }
    val week_int = when (Nweek.value) {
        "Sunday" -> 0
        "Monday" -> 1
        "Tuesday" -> 2
        "Wednesday" -> 3
        "Thursday" -> 4
        "Friday" -> 5
        "Saturday" -> 6
        else -> 6
    }
    val lastmonth: Int = Nmonth.value!! - 2 + if (Nmonth.value!! == 1) 12 else 0
    val days: Int = Nday.value!!
    val sun: Int =
        days - week_int + (if (days - week_int < 1) numberOfMonth[lastmonth] else 0) - (if (days - week_int > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0)
    val mon: Int =
        days - week_int + 1 + if (days - week_int + 1 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 1 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    val tue: Int =
        days - week_int + 2 + if (days - week_int + 2 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 2 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    val wed: Int =
        days - week_int + 3 + if (days - week_int + 3 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 3 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    val thu: Int =
        days - week_int + 4 + if (days - week_int + 4 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 4 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    val fri: Int =
        days - week_int + 5 + if (days - week_int + 5 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 5 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    val sat: Int =
        days - week_int + 6 + if (days - week_int + 6 < 1) numberOfMonth[lastmonth] else 0 - if (days - week_int + 6 > numberOfMonth[Nmonth.value!! - 1]) numberOfMonth[Nmonth.value!! - 1] else 0
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(760.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xffffffff))
                .fillMaxWidth()
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
                    text = "$month_string ${Nyear.value}",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.subtitle1
                )
                Box(modifier = modifier.size(25.dp)) {}

            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Box(modifier = modifier.size(25.dp)) {}
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SUN",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (sun == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                val flag =
                                    if (sun - Nday.value!! < -6) 1 else 0 - if (sun - Nday.value!! > 6) 1 else 0
                                Nweek.value = "Sunday"
                                Nday.value = sun
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$sun",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "MON",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (mon == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                Nweek.value = "Monday"
                                val flag =
                                    if (mon - Nday.value!! < -6) 1 else 0 - if (mon - Nday.value!! > 6) 1 else 0
                                Nday.value = mon
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$mon",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "TUE",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (tue == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                Nweek.value = "Tuesday"
                                val flag =
                                    if (tue - Nday.value!! < -6) 1 else 0 - if (tue - Nday.value!! > 6) 1 else 0
                                Nday.value = tue
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$tue",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "WED",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (wed == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                Nweek.value = "Wednesday"
                                val flag =
                                    if (wed - Nday.value!! < -6) 1 else 0 - if (wed - Nday.value!! > 6) 1 else 0
                                Nday.value = wed
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$wed",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "THU",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (thu == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                Nweek.value = "Thursday"
                                val flag =
                                    if (tue - Nday.value!! < -6) 1 else 0 - if (tue - Nday.value!! > 6) 1 else 0
                                Nday.value = thu
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$thu",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "FRI",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (fri == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                Nweek.value = "Friday"
                                val flag =
                                    if (fri - Nday.value!! < -6) 1 else 0 - if (fri - Nday.value!! > 6) 1 else 0
                                Nday.value = fri
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0
                            }
                    ) {
                        Text(
                            text = "$fri",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SAT",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (sat == Nday.value!!) Shadow3 else Color(0xffffffff))
                            .clickable {
                                val flag =
                                    if (sat - Nday.value!! < -6) 1 else 0 - if (sat - Nday.value!! > 6) 1 else 0
                                Nweek.value = "Saturday"
                                Nday.value = sat
                                Nyear.value =
                                    Nyear.value!! + (if (Nmonth.value!! == 12 && flag == 1) 1 else 0) - (if (Nmonth.value!! == 1 && flag == -1) 1 else 0)
                                Nmonth.value =
                                    Nmonth.value!! + flag + if (Nmonth.value!! + flag == 0) 12 else 0 - if (Nmonth.value!! + flag == 13) 12 else 0

                            }
                    ) {
                        Text(
                            text = "$sat",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
                Box(modifier = modifier.size(25.dp)) {}
            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .background(Shadow2)
            ) {
                Text(
                    text = "${Nyear.value} / ${Nmonth.value} / ${Nday.value}",
                    fontSize = 20.sp,
                    color = Color(0xffffffff)
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        GetCalender(
            modifier,
            navController = navController,
            year = Nyear.value!!,
            month = Nmonth.value!!,
            day = Nday.value!!,
            week = Nweek.value!!
        )
    }
}

@Composable
private fun GetCalender(
    modifier: Modifier, navController: NavHostController,
    year: Int,
    month: Int,
    day: Int,
    week: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn() {
            items(24) { data ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xfff3edf7))
                        .clickable { navController.navigate("AddCalender/${year}/${month}/${day}/${week}/${data}")}
                ) {
                    Box(modifier = Modifier.size(20.dp)) {
                        Image(
                            painter = painterResource(R.drawable.myicon),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = " $data : 00",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text =  Calenders[data] ,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(1.dp))
            }
        }
    }


}