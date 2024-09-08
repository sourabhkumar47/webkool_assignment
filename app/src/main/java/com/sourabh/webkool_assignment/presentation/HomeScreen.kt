import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModel

@Composable
fun HomeScreen(viewModel: UserViewModel = viewModel(), onUserClick: (UsersListItem) -> Unit) {
    val userList by viewModel.userList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        SearchBar(searchQuery, viewModel::onSearchQueryChanged)
        Spacer(modifier = Modifier.height(16.dp))
        UserList(userList, onUserClick)
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChanged: (String) -> Unit) {
    var query by remember { mutableStateOf(TextFieldValue(searchQuery)) }

    BasicTextField(
        value = query,
        onValueChange = {
            query = it
            onSearchQueryChanged(it.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
            Text(text = user.name)
            Text(text = user.email)
            Text(text = user.phone)
            Text(text = user.website)
            Text(text = "${user.address.street}, ${user.address.city}")
            Text(text = user.company.name)
        }
    }
}
