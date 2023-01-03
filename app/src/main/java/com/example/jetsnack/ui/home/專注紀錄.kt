package com.geeksforgeeks.jcdatepicker

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.database.Friends
import com.example.jetsnack.database.Select_index
import com.example.jetsnack.database.user
import com.example.jetsnack.ui.theme.Shadow1
import com.example.jetsnack.ui.theme.Shadow2
import java.text.SimpleDateFormat
import java.util.*

// Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content
// in the above function
@SuppressLint("SimpleDateFormat")
@Composable
fun MyContent(navController: NavHostController) {
    // Fetching the Local Context
    val mContext = LocalContext.current
    val hour = user.total_focus_time / 3600
    val minute = (user.total_focus_time % 3600)/60
    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()
    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val Simpledateformat = SimpleDateFormat("EEEE")
    val date_n: Date = Date(mYear, mMonth, mDay - 1)
    val mWeek: String = Simpledateformat.format(date_n)
    mCalendar.time = Date()
    // Declaring a string value to
    // store date in string format
    val Syear = remember { mutableStateOf(mYear) }
    val Smonth = remember { mutableStateOf(mMonth + 1) }
    val Sday = remember { mutableStateOf(mDay) }
    val mDate = remember { mutableStateOf("$mYear/${mMonth + 1}/$mDay") }
    val week = remember { mutableStateOf(mWeek) }
    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val simpledateformat = SimpleDateFormat("EEEE")
            val date = Date(mYear, mMonth, mDayOfMonth - 1)
            val dayOfWeek: String = simpledateformat.format(date)
            mDate.value = "$mYear/${mMonth + 1}/$mDayOfMonth"
            week.value = dayOfWeek
            Syear.value = mYear
            Smonth.value = mMonth + 1
            Sday.value = mDayOfMonth
        }, Syear.value, Smonth.value - 1 , Sday.value
    )
    val selectindex by remember { mutableStateOf(Select_index) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            Modifier.windowInsetsTopHeight(
                WindowInsets.statusBars.add(WindowInsets(top = 16.dp))
            )
        )
        Column(
            modifier = Modifier
                .background(Color(0xfff3edf7))
                .clip(RoundedCornerShape(24.dp))
                .width(380.dp)
                .fillMaxHeight(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            //Spacer(Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .width(340.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(Shadow1)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.width(160.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "累計專注時長",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.DarkGray
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "$hour h $minute m",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.DarkGray
                    )
                }
                Spacer(Modifier.width(18.dp))
                Column(
                    modifier = Modifier.width(160.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "朋友",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.DarkGray

                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "${Friends.size}",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.DarkGray
                    )
                }

            }
            Spacer(Modifier.height(40.dp))
            Text(text = "${user.name} 的紀錄", fontSize = 28.sp)
            Spacer(Modifier.height(48.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {

                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.navigate_before),
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "統計",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.h3
                    )
                }
                Box(
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                ) {
                    Image(
                        painter = painterResource(Dogs[selectindex].picture),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { navController.navigate("achievement") }
                ) {

                    Text(
                        text = "成就",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.navigate_after),
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = null
                        )

                    }
                }
            }
            Spacer(Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.alarm),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null
                    )
                }
                Text(
                    text = " 選擇新增待辦事項日期",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(Shadow1)
                    .clickable { mDatePickerDialog.show() }
                    .padding(top = 15.dp, bottom = 15.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = " ${mDate.value}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray

                )
                Text(
                    text = " (${week.value})",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = Color.DarkGray

                )
            }
            Spacer(Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(Shadow2)
                    .clickable { navController.navigate("calendar/${Syear.value}/${Smonth.value}/${Sday.value}/${week.value}") }
                    .padding(top = 10.dp, bottom = 10.dp, start = 40.dp, end = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "確定",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray

                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            // Adding a space of 100dp height


            // Displaying the mDate value in the Text

        }
    }
}

// For displaying preview in
// the Android Studio IDE emulator