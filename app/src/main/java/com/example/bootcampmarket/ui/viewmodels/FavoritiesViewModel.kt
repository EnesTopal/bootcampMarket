package com.example.bootcampmarket.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.entity.FavoriUrunler
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.data.repos.FavorilerRepository
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritiesViewModel @Inject constructor(
    var urunlerRepository: urunlerRepository,
    var favorilerRepository: FavorilerRepository
) : ViewModel() {
    var favorilerList = MutableLiveData<List<FavoriUrunler>>()

    init {
        loadFavoriler(kullaniciAdi = "enes_topal")
    }

    fun loadFavoriler(kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            favorilerList.value = favorilerRepository.loadFavoriler(kullaniciAdi)
        }
    }

    fun removeFavoriler(urunId: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            favorilerRepository.delete(urunId, kullaniciAdi)
        }
    }

    fun sepeteUrunEkle(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String,
        context: Context
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerRepository.sepeteUrunEkle(
                ad,
                resim,
                kategori,
                fiyat,
                marka,
                siparisAdeti,
                kullaniciAdi
            )
            Toast.makeText(context, "Ürün sepete eklendi", Toast.LENGTH_SHORT).show()
            loadFavoriler("enes_topal")
        }
    }


}