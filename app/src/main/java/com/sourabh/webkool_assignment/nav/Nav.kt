package com.sourabh.webkool_assignment.nav

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sourabh.webkool_assignment.api.ApiInterface
import com.sourabh.webkool_assignment.presentation.details_screen.PostDetailScreen
import com.sourabh.webkool_assignment.presentation.post_list_screen.UserDetailScreen
import com.sourabh.webkool_assignment.viewmodel.UserListScreen
import com.sourabh.webkool_assignment.viewmodel.UserViewModel
import com.sourabh.webkool_assignment.viewmodel.UserViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val apiInterface: ApiInterface = Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)

    NavHost(navController, startDestination = "userList") {
        composable("userList") {
            UserListScreen(viewModel = viewModel(factory = UserViewModelFactory(apiInterface)),
                onUserClick = { user ->
                    navController.navigate("userDetail/${user.id}")
                })
        }
        composable("userDetail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 1
            UserDetailScreen(
                viewModel = viewModel(factory = UserViewModelFactory(apiInterface)),
                userId = userId,
                navController = navController
            )
        }
        composable(
            "postDetail/{postId}?postJson={postJson}",
            arguments = listOf(
                navArgument("postId") { type = NavType.IntType },
                navArgument("postJson") { type = NavType.StringType }

            )
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 1
            Log.d("PostDetail", "PostId: $postId")
            val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(apiInterface))
            PostDetailScreen(viewModel = viewModel, postId = postId,backStackEntry = backStackEntry)
        }
    }
}