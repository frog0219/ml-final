package com.example.jetsnack.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.text.GetChars
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.jetsnack.database.*
import com.example.jetsnack.messages.ChatLogActivity
import com.example.jetsnack.messages.LatestMessagesActivity
import com.example.jetsnack.model.User
import com.example.jetsnack.model.userme
import com.example.jetsnack.registerLogin.RegisterActivity
import com.example.jetsnack.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FriendsList(modifier: Modifier, navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val showAddFriend = remember { mutableStateOf(false) }
    var friendCode = remember { mutableStateOf("") }
    var num by remember { mutableStateOf(Friends.size) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1.seconds)
            num = Friends.size
        }
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            Modifier.windowInsetsTopHeight(
                WindowInsets.statusBars.add(WindowInsets(top = 28.dp))
            )
        )
        SearchView(textState)
        Spacer(modifier = Modifier.height(16.dp))
        GetFriendsList(navController = navController, state = textState)

    }

}

fun addFriend(UserId1: String, UserId2: String) {
    // add userid2 to friend of userid1
    val ref = FirebaseDatabase.getInstance().getReference("/users")
    var myList: java.util.ArrayList<String> = arrayListOf()
    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            var id1 = ""
            var id2 = ""
            // Get Post object and use the values to update the UI
            p0.children.forEach {
                val user = it.getValue(User::class.java)
                if (user != null) {
                    val s1 = if (user.uid.length < 6) "aaaaaa" else user.uid.substring(0..5)
                    if (s1 == UserId1) {
                        Log.d("Fuck", s1)
                        Log.d("Fuck you", UserId1)
                        id1 = user.uid
                        user.friend.forEach {
                            myList.add(it)
                        }
                    }
                }
            }
            val ref2 = FirebaseDatabase.getInstance().getReference("/users/$id1/friend")
            var valid = false
            p0.children.forEach {
                val user = it.getValue(User::class.java)
                if (user != null) {
                    val s1 = if (user.uid.length < 6) "aaaaaa" else user.uid.substring(0..5)
                    if (s1 == UserId2) {
                        id2 = user.uid
                        valid = true
                    }
                }
            }
            if (valid) {
                myList.add(id2)
                ref2.setValue(myList)
            }

        }

        override fun onCancelled(error: DatabaseError) {

        }
    })
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .width(380.dp)
            .clip(RoundedCornerShape(20.dp)),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Shadow2,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@SuppressLint("ResourceType")
@Composable
fun GetFriendsList(navController: NavController, state: MutableState<TextFieldValue>) {
    //Log.d("???WHY",friends[0].name)
    var filtered: List<Animal>
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filtered = if (searchedText.isEmpty()) {
            Animals
        } else {
            val resultList = ArrayList<Animal>()
            for (index in Animals) {
                if (index.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(index)
                }
            }
            resultList
        }

        items(filtered) { filtered ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CountryListItem(
                    SelectAnimal = filtered,
                    navController = navController
                )
                Divider(
                    modifier = Modifier.width(380.dp),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

private lateinit var auth: FirebaseAuth
private fun fetchUsers() {
    val ref = FirebaseDatabase.getInstance().getReference("/users")
    ref.addListenerForSingleValueEvent(object : ValueEventListener {

        override fun onDataChange(p0: DataSnapshot) {
            auth = Firebase.auth
            var friends = arrayListOf("")
            p0.children.forEach {
                val user = it.getValue(User::class.java)
                if (user != null) {
                    if (user.uid == auth.uid) {
                        user.friend.forEach {
                            friends.add(it)
                        }

                    }
                }
            }
            Friends.clear()
            Users.clear()
            var i = 0
            p0.children.forEach {
                Log.d("NewMessage", it.toString())
                val user = it.getValue(User::class.java)
                if (user != null) {
                    Log.d("PLEASE", user.uid)
                    friends.forEach {
                        if (user.uid == it) {
                            //adapter.add(UserItem(user))
                            Users.add(
                                User(
                                    user.uid, user.username, user.profileImageUrl,
                                    user.friend
                                )
                            )
                            Friends.add(
                                Friend(
                                    user.uid,
                                    user.username,
                                    9,
                                    arrayListOf(1, 9, 13, 1, 29),
                                    "這個人很懶，什麼都沒留下...",
                                    i
                                )
                            )
                            i += 1
                        }
                    }

                }
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    })

}

@Composable
fun CountryListItem(SelectAnimal: Animal, navController: NavController) {
    val context = LocalContext.current
    val id = SelectAnimal.id - 1
    Row(
        modifier = Modifier
            .width(380.dp)
            .height(60.dp)
            .background(Shadow1)
            .padding(PaddingValues(8.dp, 8.dp))
            .clickable { navController.navigate("PetDetail/${id}") }
            , verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Shadow3)
                .padding(0.dp)
                .padding(5.dp)
        ) {
            Image(
                painterResource(SelectAnimal.picture),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = SelectAnimal.name, fontSize = 32.sp)
    }
}