package com.sourabh.webkool_assignment.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val api: ApiInterface) : ViewModel() {

    private val _originalUserList = MutableStateFlow<List<UsersListItem>>(emptyList())
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
                val users = api.getUsers()
                _originalUserList.value = users
                _userList.value = users
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users", e)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _userList.value = if(query.isEmpty()) {
            _originalUserList.value
        }else {
            _userList.value.filter {
                it.name.contains(query, ignoreCase = true) || it.id.toString() == query
            }
        }
    }
}
