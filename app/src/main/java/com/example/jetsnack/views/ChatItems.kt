package com.example.jetsnack.views

import android.view.View
import androidx.constraintlayout.compose.Visibility.Companion.Gone
import com.example.jetsnack.R
import com.example.jetsnack.model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

//ViewHolder Class for outbound messages
class ChatFromItem(val text: String="",val picture:String="", val user: User) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if(picture!=""){
            val targetImageView = viewHolder.itemView.photoView_from_row
            Picasso.get().load(picture).resize(700,600).into(targetImageView)
            viewHolder.itemView.textView_from_row.visibility= View.GONE
        }
        else {
            viewHolder.itemView.photoView_from_row.visibility= View.GONE
            viewHolder.itemView.textView_from_row.text = text
        }
        val uri = user.profileImageUrl
        val targetImageView = viewHolder.itemView.imageView_from_row
        Picasso.get().load(uri).into(targetImageView)

    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

//ViewHolder Class for inbound messages
class ChatToItem(val text: String="",val picture:String="", val user: User) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        if(picture!=""){
            val targetImageView = viewHolder.itemView.photoView_to_row
            Picasso.get().load(picture).resize(700,600).into(targetImageView)
            viewHolder.itemView.textView_to_row.visibility= View.GONE
        }
        else {
            viewHolder.itemView.photoView_to_row.visibility= View.GONE
            viewHolder.itemView.textView_to_row.text = text
        }

        val uri = user.profileImageUrl
        val targetImageView = viewHolder.itemView.imageView_to_row
        Picasso.get().load(uri).into(targetImageView)


    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}