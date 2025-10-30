package com.example.bootcampmarket.ui.views

import android.widget.Toast
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
    val profiller = favoritiesViewModel.profiller.observeAsState(listOf())
    val seciliProfil = favoritiesViewModel.seciliProfil.observeAsState("")

    LaunchedEffect(seciliProfil.value) {
        if (seciliProfil.value.isNotEmpty()) {
            favoritiesViewModel.loadFavoriler(seciliProfil.value)
        }
    }

    LaunchedEffect(Unit) {
        favoritiesViewModel.getProfiller()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Favoriler", profiller.value, onProfileSelected = {
                favoritiesViewModel.secilenProfiliAta(it)
                favoritiesViewModel.loadFavoriler(seciliProfil.value)
                Toast.makeText(navController.context, "Seçilen Profil: ${it}", Toast.LENGTH_SHORT).show()
            })
        },
        bottomBar = { CustomBottomBar(navController) }
    ) { paddingValues ->
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
                            kullaniciAdi = seciliProfil.value,
                            context = navController.context
                        )
                    },
                    onRemoveFavori = {
                        favori.value = false
                        favoritiesViewModel.removeFavoriler(urun.id, seciliProfil.value)
                        favoritiesViewModel.loadFavoriler(seciliProfil.value)
                    }
                )
            }
        }
    }
}