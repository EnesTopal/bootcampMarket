package com.example.bootcampmarket.ui.views

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.remember
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

    var toplamTutar = remember(urunler.value) {
        urunler.value?.sumOf { (it.fiyat ?: 0) * (it.siparisAdeti ?: 0) } ?: 0
    }

    val profiller = cartViewModel.profiller.observeAsState(listOf())
    val seciliProfil = cartViewModel.seciliProfil.observeAsState("")

    LaunchedEffect(seciliProfil.value) {
        if (seciliProfil.value.isNotEmpty()) {
            cartViewModel.postSepet(seciliProfil.value)
        }
        /*
        Null Ürün Silme
         urunler.value
        .filter { it.ad == "" }
        .forEach {
            Log.d("CartCleaning", "Silindi")
            cartViewModel.sepettenSil(it.id, seciliProfil.value) }
         */
        toplamTutar = urunler.value?.sumOf { (it.fiyat ?: 0) * (it.siparisAdeti ?: 0) } ?: 0
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Bootcamp Market", profiller.value, onProfileSelected = {
            cartViewModel.secilenProfiliAta(it)
            Toast.makeText(navController.context, "Seçilen Profil: ${it}", Toast.LENGTH_SHORT).show()
        }) },
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
                                cartViewModel.adetGuncelle(urun, yeniAdet,seciliProfil.value)
                            },
                            onAzalt = {
                                val yeniAdet = urun.siparisAdeti - 1
                                if (yeniAdet >= 1) {
                                    cartViewModel.adetGuncelle(urun, yeniAdet,seciliProfil.value)
                                }
                            },
                            onSil = {
                                cartViewModel.sepettenSil(urun.sepetId, seciliProfil.value)
                            }
                        )
                    }
                }

                Text(
                    text = "Toplam: ${toplamTutar} ₺",
                    style = MaterialTheme.typography.titleMedium
                )

                Button(onClick = {
                    cartViewModel.postSepet(seciliProfil.value)
                }) {
                    Text(text = "Sepeti Güncelle")
                }
            }
        }
    }
}