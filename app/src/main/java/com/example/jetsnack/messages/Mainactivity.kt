package com.example.jetsnack.messages

import android.provider.Settings
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.jetsnack.R
import com.example.jetsnack.database.*
import com.example.jetsnack.model.ChatMessage
import com.example.jetsnack.model.User
import com.example.jetsnack.registerLogin.LoginActivity
import com.example.jetsnack.registerLogin.RegisterActivity
import com.example.jetsnack.repository.AuthorRepo
import com.example.jetsnack.ui.JetsnackApp
import com.example.jetsnack.views.LatestMessageRow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_latest_messages.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.latest_message_row.view.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ExecutorService
import kotlin.time.Duration.Companion.seconds

var done = false

class LatestMessagesActivity : ComponentActivity() {

    companion object {
        var currentUser: User? = null
        val TAG = "LatestMessages"
        val USER_KEY = "USER_KEY"
    }
    val adapter = GroupAdapter<GroupieViewHolder>()
    // 開啟設定頁面
    private fun openPermissionSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            JetsnackApp()
        }
    }

    private lateinit var auth: FirebaseAuth
    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addValueEventListener(object : ValueEventListener {

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
                        friends.forEach {
                            if (user.uid == it && it != "") {
                                Log.d("WHY ERROR?", it)
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
                                        0,
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

    //Fetch Current User Data
    private fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                currentUser = p0.getValue(User::class.java)
                if (uid != null) {
                    user.id = uid
                }
                Log.d("LatesMessages", "Current user ${currentUser?.username}")
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    //Verify if valid User  is Logged in, if not return to Register Activity
    fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }

}