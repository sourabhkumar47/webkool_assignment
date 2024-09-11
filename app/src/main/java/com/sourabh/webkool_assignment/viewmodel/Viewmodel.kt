package com.sourabh.webkool_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.data.user_comment.UserCommentItem
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
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

    private val _originalUserPosts = MutableStateFlow<List<UserPostItem>>(emptyList())
    private val _userPosts = MutableStateFlow<List<UserPostItem>>(emptyList())
    val userPosts: StateFlow<List<UserPostItem>> = _userPosts

    private val _postSearchQuery = MutableStateFlow("")
    val postSearchQuery: StateFlow<String> = _postSearchQuery

    private val _comments = MutableStateFlow<List<UserCommentItem>>(emptyList())
    val comments: StateFlow<List<UserCommentItem>> = _comments

    init {
        fetchUsers()
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            _loading.value = true
            kotlinx.coroutines.delay(3000)
            _loading.value = false
        }
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

    fun getUserInfo(userId: Int): Flow<UserInfo> = flow {
        val userInfo = withContext(Dispatchers.IO) {
            api.getUserInfo(userId)
        }
        emit(userInfo)
    }

    fun getUserPosts(userId: Int) {
        viewModelScope.launch {
            val posts = withContext(Dispatchers.IO) {
                api.getUserPosts(userId)
            }
            _originalUserPosts.value = posts
            _userPosts.value = posts
        }
    }

    fun onHomeSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _userList.value = if (query.isEmpty()) {
            _originalUserList.value
        } else {
            _userList.value.filter {
                it.name.contains(query, ignoreCase = true) || it.id.toString() == query
            }
        }
    }

    fun onPostSearchQueryChanged(query: String) {
        _postSearchQuery.value = query
        _userPosts.value = if (query.isEmpty()) {
            _originalUserPosts.value
        } else {
            _originalUserPosts.value.filter {
                it.title.contains(query, ignoreCase = true) || it.id.toString() == query
            }
        }
    }

    fun getComments(postId: Int) {
        viewModelScope.launch {
            try {
                val comments = withContext(Dispatchers.IO) {
                    api.getComments(postId)
                }
                _comments.value = comments
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching comments", e)
            }
        }
    }
}