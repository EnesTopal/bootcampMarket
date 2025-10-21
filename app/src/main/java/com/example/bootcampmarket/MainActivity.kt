package com.example.bootcampmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bootcampmarket.ui.theme.BootcampMarketTheme
import com.example.bootcampmarket.ui.viewmodels.CartViewModel
import com.example.bootcampmarket.ui.viewmodels.DetailViewModel
import com.example.bootcampmarket.ui.viewmodels.FavoritiesViewModel
import com.example.bootcampmarket.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel : MainViewModel by viewModels()
    val detailViewModel : DetailViewModel by viewModels()
    val cartViewModel : CartViewModel by viewModels()
    val favoritiesViewModel: FavoritiesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BootcampMarketTheme {
                AppNavigation(mainViewModel,  cartViewModel, detailViewModel, favoritiesViewModel)

            }
        }
    }
}

