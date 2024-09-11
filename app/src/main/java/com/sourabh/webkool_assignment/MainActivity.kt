package com.sourabh.webkool_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sourabh.webkool_assignment.nav.AppNavigation
import com.sourabh.webkool_assignment.ui.theme.Webkool_assignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Webkool_assignmentTheme(darkTheme = false) {
                AppNavigation()
            }
        }
    }
}