package com.sourabh.webkool_assignment.presentation.post_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sourabh.webkool_assignment.data.user_detail.user_info.UserInfo

@Composable
fun UserInfoScreen(userInfo: UserInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 65.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${userInfo.name}")
            Text("Email: ${userInfo.email}")
            Text("Phone: ${userInfo.phone}")
            Text("Website: ${userInfo.website}")
            Text("Address: ${userInfo.address.street}, ${userInfo.address.suite}, ${userInfo.address.city}, ${userInfo.address.zipcode}")
            Text("Company Name: ${userInfo.company.name}")
            Text(text = "Company Address: ${userInfo.company.catchPhrase}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserInfoScreenPreview() {
    UserInfoScreen(
        UserInfo(
            address = com.sourabh.webkool_assignment.data.user_detail.user_info.Address(
                city = "Gwenborough",
                geo = com.sourabh.webkool_assignment.data.user_detail.user_info.Geo(
                    lat = "-37.3159",
                    lng = "81.1496"
                ),
                street = "Kulas Light",
                suite = "Apt. 556",
                zipcode = "92998-3874"
            ),
            company = com.sourabh.webkool_assignment.data.user_detail.user_info.Company(
                bs = "harness real-time e-markets",
                catchPhrase = "Multi-layered client-server neural-net",
                name = "Romaguera-Crona"
            ),
            email = "sou@gmail.com",
            id = 0,
            name = "name",
            phone = "4564545",
            username = "username",
            website = "sghdhdf.com"
        )
    )
}
