package com.example.bootcampmarket.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bootcampmarket.data.entity.Urunler
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UrunCard(
    urun: Urunler,
    modifier: Modifier = Modifier,
    onClick: (Urunler) -> Unit,
    onAddToCart: (Urunler) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(urun) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.size(84.dp)
            ) {
                val url = "http://kasimadalan.pe.hu/urunler/resimler/${urun.resim}"
                GlideImage(
                    imageModel = url,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = urun.ad,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = urun.marka,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${urun.fiyat} ₺",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Button(onClick = { onAddToCart(urun) }) {
                Text(text = "Sepete Ekle")
            }
        }
    }
}