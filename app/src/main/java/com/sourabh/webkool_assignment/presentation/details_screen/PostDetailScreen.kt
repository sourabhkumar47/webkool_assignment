package com.sourabh.webkool_assignment.presentation.details_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.google.gson.Gson
import com.sourabh.webkool_assignment.data.user_comment.UserCommentItem
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModel

@Composable
fun PostDetailScreen(viewModel: UserViewModel, postId: Int,backStackEntry: NavBackStackEntry) {

    val postJson = backStackEntry.arguments?.getString("postJson")
    val post = remember { Gson().fromJson(postJson, UserPostItem::class.java) }
    val comments by viewModel.comments.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getComments(postId)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Title: ${post.title}", modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Body: ${post.body}", modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Comments", modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: UserCommentItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${comment.name}")
            Text(text = "Email: ${comment.email}")
            Text(text = "Body: ${comment.body}")
        }
    }
}