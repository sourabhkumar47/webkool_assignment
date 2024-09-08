package com.sourabh.webkool_assignment.presentation.viewmodel

import androidx.lifecycle.*
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfoItem
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.data.user_comment.UserCommentItem
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.webkool_assignment.api.ApiInterface
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(private val api: ApiInterface) : ViewModel() {

    private val _userList = MutableStateFlow<List<UsersListItem>>(emptyList())
    val userList: StateFlow<List<UsersListItem>> = _userList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                _userList.value = api.getUsers()
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _userList.value = _userList.value.filter {
            it.name.contains(query, ignoreCase = true) || it.id.toString() == query
        }
    }
}
