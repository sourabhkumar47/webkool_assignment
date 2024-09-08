package com.sourabh.webkool_assignment.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, userId: Int) {
    val userPosts by viewModel.getUserPosts(userId).collectAsState(initial = emptyList())

    LazyColumn {
        items(userPosts) { post ->
            PostItem(post)
        }
    }
}

@Composable
fun PostItem(post: UserPostItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${post.title}")
            Text(text = "Body: ${post.body}")
        }
    }
}