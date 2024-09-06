package com.sourabh.webkool_assignment.data.user_list

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)