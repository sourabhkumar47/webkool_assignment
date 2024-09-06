package com.sourabh.webkool_assignment.data.user_comment

data class UserCommentItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)