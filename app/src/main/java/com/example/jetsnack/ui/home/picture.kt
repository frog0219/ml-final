package com.example.jetsnack.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.jetsnack.R
import com.example.jetsnack.database.Animals
import com.example.jetsnack.database.TensorFLowHelper
import com.example.jetsnack.database.TensorFLowHelper.imageSize
import com.example.jetsnack.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@SuppressLint("SuspiciousIndentation")
@Composable
fun ImagePicker( navController: NavHostController) {
    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            photoUri = it
        }
    )
        Column(
            Modifier
                .fillMaxSize()
                .background(Shadow0),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(48.dp))
            photoUri?.let {
                if (Build.VERSION.SDK_INT < 28)
                    bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap = ImageDecoder.decodeBitmap(
                        source,
                        ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
                            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                            decoder.isMutableRequired = true
                        })
                }
            }
            Box(Modifier.size(350.dp).clickable { launcher.launch("image/*") }) {
                if(bitmap == null){
                    Image(
                        painterResource(Animals[0].picture),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Image from the gallery",
                        Modifier.fillMaxSize()
                    )
                }
            }
            bitmap?.let {
                Spacer(modifier = Modifier.padding(24.dp))
                val scaledBitmap = Bitmap.createScaledBitmap(it, imageSize, imageSize, false);
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
                            modifier = Modifier.size(50.dp).clickable {navController.navigate("PetDetail/${id}")}
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
                }
            }
            Spacer(modifier = Modifier.padding(24.dp))
            Row( modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Press the dog to select a picture!" , fontSize = 20.sp , color = Color.Gray)
            }
        }
}
