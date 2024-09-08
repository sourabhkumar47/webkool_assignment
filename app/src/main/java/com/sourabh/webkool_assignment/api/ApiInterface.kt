package com.sourabh.webkool_assignment.api

import com.sourabh.webkool_assignment.data.user_comment.UserCommentItem
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo
import com.sourabh.webkool_assignment.data.user_detail.user_post.UserPostItem
import com.sourabh.webkool_assignment.data.user_list.UsersListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users")
    suspend fun getUsers(): List<UsersListItem>

    @GET("/users/{id}")
    suspend fun getUserInfo(@Path("id") userId: Int): UserInfo

    @GET("/users/{id}/posts")
    suspend fun getUserPosts(@Path("id") userId: Int): List<UserPostItem>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): List<UserCommentItem>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}