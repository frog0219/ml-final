package com.example.jetsnack.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Animals
import com.example.jetsnack.database.TensorFLowHelper
import com.example.jetsnack.messages.LatestMessagesActivity
import com.example.jetsnack.ui.theme.Shadow0
import com.example.jetsnack.ui.theme.Shadow5


@Composable
fun CaptureImageFromCamera(navController : NavHostController) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            bitmap = it
        }
    Column( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Shadow0),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center){
        bitmap?.let {
            Spacer(modifier = Modifier.padding(48.dp))
            Box(Modifier.size(350.dp)) {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Image from the gallery",
                    Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            val scaledBitmap = Bitmap.createScaledBitmap(it,
                TensorFLowHelper.imageSize,
                TensorFLowHelper.imageSize, false);
            val id = TensorFLowHelper.classifyImage(scaledBitmap)
            val name = Animals[id].name
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "The picture is classified as:" , fontSize = 24.sp)
                Spacer(modifier = Modifier.padding(12.dp))
                Row(modifier = Modifier
                    .width(200.dp)
                    .height(50.dp) ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { navController.navigate("PetDetail/${id}") }
                    ) {
                        Image(
                            painterResource(R.drawable.search_new),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = name, fontSize = 42.sp , color = Color.Black , fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        Box(
            Modifier
                .size(60.dp)
                .clickable {
                    if((ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_GRANTED)) launcher.launch()
                }) {
            Image(
                painter = painterResource(R.drawable.photo),
                contentDescription = "",
                Modifier.fillMaxSize()
            )
        }
    }
}

