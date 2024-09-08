package com.sourabh.webkool_assignment.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModel

@Composable
fun HomeScreen(viewModel: UserViewModel = viewModel(), onUserClick: (UsersListItem) -> Unit) {
    val userList by viewModel.userList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        SearchBar(searchQuery, viewModel::onSearchQueryChanged)
        Spacer(modifier = Modifier.height(6.dp))
        UserList(userList, onUserClick)
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChanged: (String) -> Unit) {
    var query by remember { mutableStateOf(searchQuery) }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearchQueryChanged(it)
        },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
            .height(62.dp),
        shape = RoundedCornerShape(24.dp),
        label = { Text("Search") }
    )
}

@Composable
fun UserList(userList: List<UsersListItem>, onUserClick: (UsersListItem) -> Unit) {
    LazyColumn {
        items(userList) { user ->
            UserItem(user, onUserClick)
        }
    }
}

@Composable
fun UserItem(user: UsersListItem, onUserClick: (UsersListItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onUserClick(user) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${user.name}")
            Text(text = "Email: ${user.email}")
            Text(text = "Phone: ${user.phone}")
            Text(text = "Website: ${user.website}")
            Text(text = "Address: ${user.address.street}, ${user.address.city}")
            Text(text = "Company: ${user.company.name}")
        }
    }
}
