package com.example.bootcampmarket.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.ui.components.CustomTopAppBar
import com.example.bootcampmarket.ui.viewmodels.DetailViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.example.bootcampmarket.R
import com.example.bootcampmarket.ui.components.CustomBottomBar

@Composable
fun DetailScreen(navController: NavController, detailViewModel: DetailViewModel, urun : Urunler) {
    val urunDetail = urun
    val adetCounter = remember { mutableIntStateOf(1) }
    val profiller = detailViewModel.profiller.observeAsState(listOf())
    val seciliProfil = detailViewModel.seciliProfil.observeAsState("")

    LaunchedEffect(true) {
        detailViewModel.getProfiller()
        detailViewModel.seciliProfiliGetir()
    }



    Scaffold(
        topBar = { CustomTopAppBar(title = "Bootcamp Market", profiller.value, onProfileSelected = {
            detailViewModel.seciliProfil.value = it
            Toast.makeText(navController.context, "Seçilen Profil: ${it}", Toast.LENGTH_SHORT).show()
        }) },
        bottomBar = { CustomBottomBar(navController) }
    ) { padding ->
        urunDetail?.let { u ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.size(150.dp)
                ) {
                    val url = "http://kasimadalan.pe.hu/urunler/resimler/${u.resim}"
                    GlideImage(
                        imageModel = url,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = u.ad, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = u.marka, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = u.kategori, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Fiyat: ${u.fiyat} ₺", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (adetCounter.value > 1) {
                            adetCounter.value = adetCounter.value - 1
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "Azalt"
                        )
                    }
                    Text(
                        text = "${adetCounter.value}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    IconButton(onClick = { adetCounter.value += 1 }) {
                        Icon(Icons.Default.Add, contentDescription = "Arttır")
                    }
                }


                Button(onClick = {
                    detailViewModel.sepeteUrunEkle(
                        ad = u.ad,
                        resim = u.resim,
                        kategori = u.kategori,
                        fiyat = u.fiyat,
                        marka = u.marka,
                        siparisAdeti = adetCounter.value,
                        kullaniciAdi = seciliProfil.value,
                        context = navController.context
                    )
                    adetCounter.value = 1
                }) {
                    Text(text = "Sepete Ekle")
                }
            }
        } ?: run {
            Text(
                text = "Ürün yükleniyor...",
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}