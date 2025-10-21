package com.example.bootcampmarket.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.ui.components.CustomBottomBar
import com.example.bootcampmarket.ui.components.CustomTopAppBar
import com.example.bootcampmarket.ui.components.SepetCard
import com.example.bootcampmarket.ui.viewmodels.CartViewModel

@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val urunler = cartViewModel.urunlerList.observeAsState(emptyList())

    var toplamTutar = urunler.value?.sumOf { (it.fiyat ?: 0) * (it.siparisAdeti ?: 0) } ?: 0

    LaunchedEffect(urunler.value) {
        cartViewModel.postSepet("enes_topal")
//        urunler.value
//            .filter { it.ad == "" }
//            .forEach {
//                Log.d("CartCleaning", "Silindi")
//                cartViewModel.sepettenSil(it.id, "enes_topal") }
        toplamTutar = urunler.value?.sumOf { (it.fiyat ?: 0) * (it.siparisAdeti ?: 0) } ?: 0
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Sepetim") },
        bottomBar = { CustomBottomBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (urunler.value.isEmpty()) {
                Text(text = "Sepet boş", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(urunler.value ?: emptyList()) { urun ->
                        SepetCard(
                            urun = urun,
                            onArttir = {
                                val yeniAdet = urun.siparisAdeti + 1
                                cartViewModel.adetGuncelle(urun, yeniAdet,"enes_topal")
                            },
                            onAzalt = {
                                val yeniAdet = urun.siparisAdeti - 1
                                if (yeniAdet >= 1) {
                                    cartViewModel.adetGuncelle(urun, yeniAdet,"enes_topal")
                                }
                            },
                            onSil = {
                                cartViewModel.sepettenSil(urun.sepetId, "enes_topal")
                            }
                        )
                    }
                }

                Text(
                    text = "Toplam: ${toplamTutar} ₺",
                    style = MaterialTheme.typography.titleMedium
                )

                Button(onClick = {
                    cartViewModel.postSepet("enes_topal")
                }) {
                    Text(text = "Sepeti Güncelle")
                }
            }
        }
    }
}