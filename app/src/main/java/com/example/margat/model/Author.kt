package com.example.margat.model

import com.stfalcon.chatkit.commons.models.IUser

data class Author(
    private val id:String,
    private val name: String,
    private val avatar: String): IUser {

    override fun getId(): String {
        return id
    }

    override fun getName(): String {
        return name
    }

    override fun getAvatar(): String {
        return avatar
    }


}