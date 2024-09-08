package com.sourabh.webkool_assignment.data.user_detail.user_info

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)