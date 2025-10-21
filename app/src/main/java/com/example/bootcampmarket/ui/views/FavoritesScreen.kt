package com.example.bootcampmarket.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.ui.components.CustomBottomBar
import com.example.bootcampmarket.ui.components.CustomTopAppBar
import com.example.bootcampmarket.ui.components.FavoriCard
import com.example.bootcampmarket.ui.viewmodels.FavoritiesViewModel
import com.google.gson.Gson

@Composable
fun FavoritesScreen(navController: NavController, favoritiesViewModel: FavoritiesViewModel) {
    val favoriler = favoritiesViewModel.favorilerList.observeAsState(listOf())

    LaunchedEffect(true) {
        favoritiesViewModel.loadFavoriler("enes_topal")
    }

    Scaffold(
        topBar = { CustomTopAppBar("Favoriler") },
        bottomBar = { CustomBottomBar(navController) }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(favoriler.value) { urun ->
                val favori = remember(urun.id) { mutableStateOf(true) }
                FavoriCard(
                    favoriUrun = urun,
                    favoriChecked = favori.value,
                    modifier = Modifier.padding(bottom = 12.dp),
                    onClick = { clicked ->
                        val urunJson = Gson().toJson(clicked)
                        navController.navigate("detailScreen/${urunJson}")
                    },
                    onAddToCart = { added ->
                        favoritiesViewModel.sepeteUrunEkle(
                            ad = added.ad,
                            resim = added.resim,
                            kategori = added.kategori,
                            fiyat = added.fiyat,
                            marka = added.marka,
                            siparisAdeti = 1,
                            kullaniciAdi = "enes_topal",
                            context = navController.context
                        )
                    },
                    onRemoveFavori = {
                        favori.value = false
                        favoritiesViewModel.removeFavoriler(urun.id, "enes_topal")
                        favoritiesViewModel.loadFavoriler("enes_topal")
                    }
                )
            }
        }
    }
}