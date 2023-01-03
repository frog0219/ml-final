package com.example.jetsnack.model

//Chat Message Model Class
class ChatMessage(
    val id: String,
    val text: String,
    val fromId: String,
    val toId: String,
    var photoUrl: String? = null,
    var imageUrl: String? = null,
    val timestamp: Long
) {
    constructor() : this("", "", "", "", "","",-1)
}