package com.sourabh.webkool_assignment.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.presentation.detail_screen.UserDetailScreen
import com.sourabh.webkool_assignment.presentation.viewmodel.UserListScreen
import com.sourabh.webkool_assignment.presentation.viewmodel.UserViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Initialize ApiInterface
    val apiInterface: ApiInterface = Retrofit.Builder()
        .baseUrl(ApiInterface.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    NavHost(navController, startDestination = "userList") {
        composable("userList") {
            UserListScreen(
                viewModel = viewModel(factory = UserViewModelFactory(apiInterface)),
                onUserClick = { user ->
                    navController.navigate("userDetail/${user.id}")
                }
            )
        }
        composable("userDetail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 1
            UserDetailScreen(viewModel = viewModel(factory = UserViewModelFactory(apiInterface)), userId = userId)
        }
    }
}
