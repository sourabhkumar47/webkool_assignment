import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
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
            .height(56.dp),
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
