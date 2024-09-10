package com.sourabh.webkool_assignment.presentation.post_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem

@Composable
fun PostItem(post: UserPostItem, navController:NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val postJson = Gson().toJson(post)
                navController.navigate("postDetail/${post.id}?postJson=$postJson")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${post.title}")
            Text(text = "Body: ${post.body}")
        }
    }
}