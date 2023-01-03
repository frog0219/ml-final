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
import com.example.jetsnack.ui.components.JetsnackDivider
import com.example.jetsnack.ui.theme.*

@Composable
fun CommonQuestion(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            /*Spacer(Modifier.height(48.dp))*/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
                            .clickable { navController.popBackStack()}
                    )
                }
                Text(text = "常見問題", fontSize = 24.sp)
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                ) {
                }
            }
            Divider(
                modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
                color = Color.DarkGray,
                thickness = 1.dp,
            )
            Spacer(Modifier.height(20.dp))
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().clip(RoundedCornerShape(10.dp)).background(Shadow1)) {
                Spacer(Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    Image(
                        painterResource(R.drawable.problem1),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    color = Color.Gray,
                    thickness = 1.dp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                ) {
                    Image(
                        painterResource(R.drawable.problem2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }

}

@Preview("12")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun CommonQuestionPreview() {
    JetsnackTheme {
        //CommonQuestion()
    }
}
