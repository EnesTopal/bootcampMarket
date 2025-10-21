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
import com.example.bootcampmarket.ui.viewmodels.FavoritiesViewModel
import com.example.bootcampmarket.ui.views.CartScreen
import com.example.bootcampmarket.ui.views.DetailScreen
import com.example.bootcampmarket.ui.views.FavoritesScreen
import com.example.bootcampmarket.ui.views.MainScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    detailViewModel: DetailViewModel,
    favoritiesViewModel: FavoritiesViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }

        composable(
            "detailScreen/{urun}",
            arguments = listOf(
                navArgument("urun") { type = NavType.StringType }
            )
        ) {
            val jsonUrun = it.arguments?.getString("urun")
            val urun = Gson().fromJson(jsonUrun, Urunler::class.java)
            DetailScreen(navController = navController, detailViewModel = detailViewModel, urun = urun)
        }

        composable("cartScreen") {
            CartScreen(navController = navController, cartViewModel = cartViewModel)
        }

        composable("favoritesScreen") {
            FavoritesScreen(navController = navController, favoritiesViewModel = favoritiesViewModel)
        }
    }

}

