package com.example.bootcampmarket.ui.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.data.entity.FavoriUrunler
import com.example.bootcampmarket.ui.components.CustomBottomBar
import com.example.bootcampmarket.ui.components.CustomTopAppBar
import com.example.bootcampmarket.ui.components.UrunCard
import com.example.bootcampmarket.ui.viewmodels.MainViewModel
import com.google.gson.Gson

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val urunler = mainViewModel.urunlerList.observeAsState(listOf())
    val profiller = mainViewModel.profiller.observeAsState(listOf())
    val seciliProfil = mainViewModel.seciliProfil.observeAsState("")


    LaunchedEffect(true) {
        mainViewModel.loadUrunler()
        mainViewModel.getProfiller()
        mainViewModel.seciliProfiliGetir()
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Bootcamp Market", profiller.value, onProfileSelected = {
            mainViewModel.secilenProfiliAta(it)
            mainViewModel.loadUrunler()
            Toast.makeText(navController.context, "Seçilen Profil: ${it}", Toast.LENGTH_SHORT).show()
        }) },
        bottomBar = { CustomBottomBar(navController) }
    ) { padding ->
        if (urunler.value.isEmpty()) {
            Text(
                text = "Ürün bulunamadı",
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(urunler.value) { urun ->
                    val favori = remember(urun.id, seciliProfil.value) { mutableStateOf(false) }
                    LaunchedEffect(key1 = urun.id, key2 = seciliProfil.value) {
                        mainViewModel.favorilerTablosundaVarmi(urun.id, seciliProfil.value, onResult = {
                            favori.value = it
                        })
                    }
                    UrunCard(
                        urun = urun,
                        favoriChecked = favori.value,
                        modifier = Modifier.padding(bottom = 12.dp),
                        onClick = { clicked ->
                            val urunJson = Gson().toJson(clicked)
                            navController.navigate("detailScreen/${urunJson}")
                        },
                        onAddToCart = { added ->
                            mainViewModel.sepeteUrunEkle(
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
                        onAddFavori = {
                            favori.value = true
                            val favoriUrun = FavoriUrunler(
                                favoriId = 0,
                                id = urun.id,
                                ad = urun.ad,
                                resim = urun.resim,
                                kategori = urun.kategori,
                                fiyat = urun.fiyat,
                                marka = urun.marka,
                                kullaniciAdi = seciliProfil.value
                            )
                            mainViewModel.addFavoriler(favoriUrun)
                            mainViewModel.loadUrunler()
                        },
                        onRemoveFavori = {
                            favori.value = false
                            mainViewModel.removeFavoriler(urun.id, seciliProfil.value)
                            mainViewModel.loadUrunler()
                        }
                    )
                }
            }
        }
    }
}