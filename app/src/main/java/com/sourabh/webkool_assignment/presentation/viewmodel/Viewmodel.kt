package com.sourabh.webkool_assignment.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import com.sourabh.webkool_assignment.di.apiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val api: ApiInterface) : ViewModel() {

    private val _originalUserList = MutableStateFlow<List<UsersListItem>>(emptyList())
    private val _userList = MutableStateFlow<List<UsersListItem>>(emptyList())
    val userList: StateFlow<List<UsersListItem>> = _userList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading


    init {
        fetchUsers()
        loadUserInfo()
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

    fun getUserPosts(userId: Int): Flow<List<UserPostItem>> = flow {
        val posts = withContext(Dispatchers.IO) {
            api.getUserPosts(userId)
        }
        emit(posts)
    }

    fun getUserInfo(userId: Int): Flow<UserInfo> = flow {
        val userInfo = withContext(Dispatchers.IO) {
            api.getUserInfo(userId)
        }
        emit(userInfo)
    }

    fun loadUserInfo() {
        viewModelScope.launch {
            _loading.value = true
            kotlinx.coroutines.delay(3000) // 3 seconds delay
            _loading.value = false
        }
    }


}
