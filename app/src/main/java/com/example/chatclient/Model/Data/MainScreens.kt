package com.example.chatclient.Model.Data

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.chatclient.Controller.ChatFragment
import com.example.chatclient.Controller.TopFragment
import com.example.chatclient.R

enum class MainScreen(
    @IdRes val menuItemId: Int,
    val fragment: Fragment
) {
    Chat(
        R.id.chat,
        ChatFragment()
    ),
    Top(
        R.id.top,
        TopFragment()
    )
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}