package com.sourabh.webkool_assignment.presentation.post_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sourabh.webkool_assignment.data.user_detail.user_info.Address
import com.sourabh.webkool_assignment.data.user_detail.user_info.Company
import com.sourabh.webkool_assignment.data.user_detail.user_info.Geo
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo
import com.sourabh.webkool_assignment.presentation.SearchBar
import com.sourabh.webkool_assignment.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, userId: Int, navController: NavController) {
    val userPosts by viewModel.userPosts.collectAsState()
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
    val postSearchQuery by viewModel.postSearchQuery.collectAsState()

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(userId) {
        viewModel.getUserPosts(userId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                when (selectedTabIndex) {
                    0 -> UserInfoScreen(userInfo)
                    1 -> {
                        Column(modifier = Modifier.padding(12.dp)) {
                            SearchBar(postSearchQuery, viewModel::onPostSearchQueryChanged)
                            LazyColumn(modifier = Modifier.padding(bottom = 36.dp)) {
                                items(userPosts) { post ->
                                    PostItem(post, navController = navController)
                                }
                            }
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