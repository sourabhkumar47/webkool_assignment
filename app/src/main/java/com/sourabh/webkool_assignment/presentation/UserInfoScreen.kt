package com.sourabh.webkool_assignment.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo

@Composable
fun UserInfoScreen(userInfo: UserInfo) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Name: ${userInfo.name}")
        Text("Username: ${userInfo.username}")
        Text("Email: ${userInfo.email}")
        Text("Phone: ${userInfo.phone}")
        Text("Website: ${userInfo.website}")
        Text("Address: ${userInfo.address.street}, ${userInfo.address.suite}, ${userInfo.address.city}, ${userInfo.address.zipcode}")
        Text("Company: ${userInfo.company.name}, ${userInfo.company.catchPhrase}, ${userInfo.company.bs}")
    }
}