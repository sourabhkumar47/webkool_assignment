package com.sourabh.webkool_assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sourabh.webkool_assignment.nav.AppNavigation
import com.sourabh.webkool_assignment.ui.theme.Webkool_assignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Webkool_assignmentTheme(darkTheme = false) {
                AppNavigation()
                Log.d("MainActivity", "onCreate: ")
            }
        }
    }
}