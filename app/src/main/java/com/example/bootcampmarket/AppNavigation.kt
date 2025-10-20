package com.example.bootcampmarket

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.ui.viewmodels.MainViewModel
import com.example.bootcampmarket.ui.viewmodels.DetailViewModel
import com.example.bootcampmarket.ui.viewmodels.CartViewModel
import com.example.bootcampmarket.ui.views.CartScreen
import com.example.bootcampmarket.ui.views.DetailScreen
import com.example.bootcampmarket.ui.views.MainScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    detailViewModel: DetailViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }

        composable(
            "detailScreen/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: return@composable
            DetailScreen(navController = navController, detailViewModel = detailViewModel, id = id)
        }

        composable("cartScreen") {
            CartScreen(navController = navController, cartViewModel = cartViewModel)
        }
    }

}


//        composable(
//            "updateScreen/{toDo}",
//            arguments = listOf(
//                navArgument("toDo") { type = NavType.StringType }
//            )
//        ) {
//            val jsonToDo = it.arguments?.getString("toDo")
//            val toDo = Gson().fromJson(jsonToDo, ToDos::class.java)
//            if (toDo != null) {
//                UpdateScreen(toDo = toDo, updateViewModel = updateViewModel)
//            }
//        }