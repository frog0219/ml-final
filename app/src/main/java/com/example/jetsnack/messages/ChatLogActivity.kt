package com.example.jetsnack.messages

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.jetsnack.R
import com.example.jetsnack.messages.LatestMessagesActivity.Companion.currentUser
import com.example.jetsnack.model.FriendlyMessage
import com.example.jetsnack.model.ChatMessage
import com.example.jetsnack.model.User
import com.example.jetsnack.views.ChatFromItem
import com.example.jetsnack.views.ChatToItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*
import java.util.*

class ChatLogActivity : AppCompatActivity() {
    companion object {
        val TAG = "ChatLog"
        private const val LOADING_IMAGE_URL = "https://www.google.com/images/spin-32.gif"
    }

    val adapter = GroupAdapter<GroupieViewHolder>()

    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        recyclerview_chat_log.adapter = adapter

        toUser = intent.getParcelableExtra<User>(LatestMessagesActivity.USER_KEY)

        supportActionBar?.title = toUser?.username

        listenForMessages()

        //Button click listener
        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "Attemp to send")
            performSendMessage()
        }
        select_photo_button_register.setOnClickListener {
            Log.d(TAG, "Try to select photo")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }
    var selectedPhotoUri: Uri? = null

    //Fetch Photo Uri data from ACTION_PICK Result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
        }
        uploadImageToFirebaseStorage()
    }
    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return
        val text = ""
        val fromId = FirebaseAuth.getInstance().uid

        //Get receiver info
        val user = intent.getParcelableExtra<User>(LatestMessagesActivity.USER_KEY)
        val toId = user?.uid
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        val reference =
            FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toReference =
            FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener { taskSnapshot -> // After the image loads, get a public downloadUrl for the image
                // and add it to the message.
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        val chatMessage =
                            ChatMessage(reference.key!!, text, fromId!!, toId!!,uri.toString(),LOADING_IMAGE_URL, System.currentTimeMillis() / 1000)
                        reference.setValue(chatMessage)
                        toReference.setValue(chatMessage)
                    }
                Log.d("Register", "Succesfully uploaded image: ${taskSnapshot.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {

                    Log.d("RegisterActivity", "File Location: $it")

                    //saveUserToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {

            }
    }

    private fun listenForMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        //Fetch Data of messages from Firebase
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, previousChildName: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    Log.d(TAG, chatMessage.text)

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser = LatestMessagesActivity.currentUser ?: return
                        if(chatMessage.photoUrl!=""){
                            adapter.add(ChatFromItem(picture =chatMessage.photoUrl!!, user=currentUser))
                        }
                        else
                        adapter.add(ChatFromItem(text=chatMessage.text, user=currentUser))

                    } else {
                        if(chatMessage.photoUrl!=""){
                            adapter.add(ChatToItem(picture =chatMessage.photoUrl!!, user=toUser!!))
                        }
                        else
                        adapter.add(ChatToItem(text=chatMessage.text, user=toUser!!))
                    }

                }

                //scroll to newest message
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)

            }

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }
        })

    }


    private fun performSendMessage() {

        //Get sender info
        val text = edittext_chat_log.text.toString()
        val fromId = FirebaseAuth.getInstance().uid

        //Get receiver info
        val user = intent.getParcelableExtra<User>(LatestMessagesActivity.USER_KEY)
        val toId = user?.uid

        if (fromId == null) return

        //Push messages to each user message's list on the RealTimeDatabase
        val reference =
            FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()

        val toReference =
            FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val chatMessage =
            ChatMessage(reference.key!!, text, fromId, toId!!,"",LOADING_IMAGE_URL, System.currentTimeMillis() / 1000)
        reference.setValue(chatMessage)

            .addOnSuccessListener {
                Log.d(TAG, "Saved chat message: ${reference.key}")
                edittext_chat_log.text.clear()
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }
        toReference.setValue(chatMessage)

        //Update Latest message to the recently uploaded message
        val latestMessageRef =
            FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef =
            FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestMessageToRef.setValue(chatMessage)

    }


}

