package com.example.chatclient.Model.Data

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.chatclient.Controller.ChatFragment
import com.example.chatclient.Controller.TopFragment
import com.example.chatclient.R

enum class MainScreen(
    @IdRes val menuItemId: Int,
    @DrawableRes val menuItemIconId: Int,
    @StringRes val titleStringId: Int,
    val fragment: Fragment
) {
    Chat(
        R.id.chat,
        R.drawable.ic_chat_blue_24dp,
        R.string.chat,
        ChatFragment()
    ),
    Top(
        R.id.top,
        R.drawable.ic_people_blue_24dp,
        R.string.top,
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