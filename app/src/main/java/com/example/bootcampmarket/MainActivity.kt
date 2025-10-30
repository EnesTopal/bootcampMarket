package com.example.bootcampmarket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
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
import androidx.core.view.WindowCompat
import com.example.bootcampmarket.ui.theme.BootcampMarketTheme
import com.example.bootcampmarket.ui.viewmodels.CartViewModel
import com.example.bootcampmarket.ui.viewmodels.DetailViewModel
import com.example.bootcampmarket.ui.viewmodels.FavoritiesViewModel
import com.example.bootcampmarket.ui.viewmodels.MainViewModel
import com.example.bootcampmarket.ui.viewmodels.ProfilesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel : MainViewModel by viewModels()
    val detailViewModel : DetailViewModel by viewModels()
    val cartViewModel : CartViewModel by viewModels()
    val favoritiesViewModel: FavoritiesViewModel by viewModels()
    val profilesViewModel: ProfilesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        hideSystemUI(window)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BootcampMarketTheme {
                AppNavigation(mainViewModel,  cartViewModel, detailViewModel, favoritiesViewModel, profilesViewModel)

            }
        }
    }

    @SuppressLint("InlinedApi", "WrongConstant")
    fun hideSystemUI(window: Window) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller?.let {
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            it.hide(WindowInsets.Type.navigationBars() or WindowInsets.Type.statusBars())
        }
    }
}

