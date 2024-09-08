package com.sourabh.webkool_assignment.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sourabh.webkool_assignment.data.user_detail.user_info.Address
import com.sourabh.webkool_assignment.data.user_detail.user_info.Company
import com.sourabh.webkool_assignment.data.user_detail.user_info.Geo
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun UserDetailScreen(viewModel: UserViewModel, userId: Int) {
    val userPosts by viewModel.getUserPosts(userId).collectAsState(initial = emptyList())
    val userInfo by viewModel.getUserInfo(userId).collectAsState(
        initial = UserInfo(
            address = Address("", Geo("", ""), "", "", ""),
            company = Company("", "", ""),
            email = "",
            id = 0,
            name = "",
            phone = "",
            username = "",
            website = ""
        )
    )

    val loading by viewModel.loading.collectAsState()

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                when (selectedTabIndex) {
                    0 -> UserInfoScreen(userInfo)
                    1 -> LazyColumn {
                        items(userPosts) { post ->
                            PostItem(post)
                        }
                    }
                }
            }
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()

            ) {
                Tab(
                    text = { Text("User Info") },
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 }
                )
                Tab(
                    text = { Text("Posts") },
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 }
                )
            }
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