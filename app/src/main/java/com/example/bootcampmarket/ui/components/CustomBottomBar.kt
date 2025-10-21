package com.example.bootcampmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.R

@Composable
fun CustomBottomBar(navController: NavController) {
    BottomAppBar(modifier = Modifier.height(100.dp)){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {navController.navigate("mainScreen")}) {
                Icon(
                    painter = painterResource(R.drawable.home_icon),
                    contentDescription = "mainScreen"
                )
            }
            IconButton(onClick = {navController.navigate("cartScreen")}) {
                Icon(
                    painter = painterResource(R.drawable.shopping_cart),
                    contentDescription = "cart"
                )
            }
            IconButton(onClick = {navController.navigate("favoritesScreen")}) {
                Icon(
                    painter = painterResource(R.drawable.heart_regular_full),
                    contentDescription = "favorites"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.user_icon),
                    contentDescription = "profile"
                )
            }
        }
    }
}