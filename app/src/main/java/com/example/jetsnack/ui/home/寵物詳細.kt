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
import com.example.jetsnack.database.Animal
import com.example.jetsnack.database.Animals
import com.example.jetsnack.database.Dogs
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*

@Composable
fun PetDetails(
    modifier: Modifier = Modifier,
    id : Int?,
    navController: NavHostController
) {
Column( horizontalAlignment = Alignment.CenterHorizontally) {
    Spacer(Modifier.height(48.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(760.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Shadow1)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
            ) {
                Image(
                    painterResource(R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.popBackStack() }
                )
            }
        }
        Spacer(Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        ) {
            Image(
                painterResource(Animals[id!!].picture),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(text = Animals[id!!].name, fontSize = 35.sp)
        Spacer(Modifier.height(24.dp))
        Divider(
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.Gray,
            thickness = 1.dp,
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(Modifier.height(18.dp))
            Text(text = Animals[id!!].intro1, fontSize = 18.sp)
            Text(text = Animals[id!!].intro2, fontSize = 18.sp)
            Text(text = Animals[id!!].intro3, fontSize = 18.sp)
            Spacer(Modifier.height(18.dp))
        }
    }
}

}
