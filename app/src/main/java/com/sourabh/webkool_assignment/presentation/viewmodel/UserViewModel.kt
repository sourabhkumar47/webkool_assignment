package com.sourabh.webkool_assignment.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import com.sourabh.webkool_assignment.di.apiInterface
import com.sourabh.webkool_assignment.presentation.HomeScreen

@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(apiInterface)), onUserClick: (UsersListItem) -> Unit) {
    HomeScreen(viewModel, onUserClick)
}

class UserViewModelFactory(private val api: ApiInterface) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
