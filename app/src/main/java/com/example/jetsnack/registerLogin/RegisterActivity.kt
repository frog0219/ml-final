package com.example.jetsnack.registerLogin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.jetsnack.R
import com.example.jetsnack.database.user
import com.example.jetsnack.messages.LatestMessagesActivity
import com.example.jetsnack.model.User
import com.example.jetsnack.repository.AuthorRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.jetsnack.usecase.GraphqlUsecase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set buttons' click listeners
        register_button_register.setOnClickListener {
            performRegister()
        }

        already_have_account_text_view.setOnClickListener {
            Log.d("RegisterActivity", "Login activity")

            //launch login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        /*selectphoto_button_register.setOnClickListener {
            Log.d("RegisterActivity", "Try to select photo")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        */
         */
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        //Verify that data has been entered
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email and password", Toast.LENGTH_SHORT)
                .show()
            return
        }

        Log.d("RegisterActivity", "Email is:" + email)
        Log.d("RegisterActivity", "Password is:" + password)

        //Register user onto Firebase Auth
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("RegisterActivity", "Succesfully created user ${it.result?.user?.uid}")
                saveUserToFirebaseDatabase(it.toString())

                //uploadImageToFirebaseStorage()

            }
            .addOnFailureListener {
                Log.d("RegisterActivity", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }

    }

    //Save Image In Firebase Storage


    private lateinit var db: FirebaseDatabase

    //Save User Data unto Realtime Database
    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val init = arrayListOf("hIEi8IlhSNSoKN5sK8AseYCifpj1")
        addFriend("hIEi8IlhSNSoKN5sK8AseYCifpj1", uid)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.drawable.animal_01)
        val authorRepo = AuthorRepo()

        GlobalScope.launch(Dispatchers.Main) {
            authorRepo.createAccount(uid , username_edittext_register.text.toString()  , 1)
            //val name=authorRepo.getHatch(uid)
            //Log.d("TEXT SQL", name.toString())

        }
        val user = User(uid, username_edittext_register.text.toString(), uri.toString(), init)
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "saved user to database")

                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {

            }
    }
    private fun addFriend(UserId1: String, UserId2: String) {
        // add userid2 to friend of userid1
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        var myList: ArrayList<String> = arrayListOf()
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                // Get Post object and use the values to update the UI
                p0.children.forEach {
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        if (user.uid == UserId1) {
                            user.friend.forEach {
                                myList.add(it)
                            }
                        }
                    }
                }
                val ref2 = FirebaseDatabase.getInstance().getReference("/users/$UserId1/friend")
                var valid = false
                p0.children.forEach {
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        if (user.uid == UserId2) {
                            valid = true
                        }
                    }
                }
                if (valid) {
                    myList.add(UserId2)
                    ref2.setValue(myList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}

