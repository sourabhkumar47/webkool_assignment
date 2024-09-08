package com.sourabh.webkool_assignment.data.user_detail.user_info

data class UserInfo(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)