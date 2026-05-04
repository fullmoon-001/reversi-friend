package com.example.reversifriend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reversifriend.ui.ReversiFriendApp
import com.example.reversifriend.ui.theme.ReversiFriendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReversiFriendTheme {
                ReversiFriendApp()
            }
        }
    }
}

