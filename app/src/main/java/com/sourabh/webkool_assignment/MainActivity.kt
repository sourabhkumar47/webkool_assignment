package com.sourabh.webkool_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.sourabh.webkool_assignment.api.isNetworkAvailable
import com.sourabh.webkool_assignment.nav.AppNavigation
import com.sourabh.webkool_assignment.ui.theme.Webkool_assignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Webkool_assignmentTheme(darkTheme = false) {
                if (isNetworkAvailable(this)) {
                    AppNavigation()
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.nointernet),
                                contentDescription = "No Internet Connection"
                            )
                            Text(
                                text = "No Internet",
                                style = TextStyle(fontSize = 20.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}