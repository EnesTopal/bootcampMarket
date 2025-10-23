package com.example.bootcampmarket.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.datasources.ProfileStore
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    var urunlerRepository: urunlerRepository,
    var profileStore: ProfileStore
) : ViewModel() {
    val urun = MutableLiveData<Urunler?>(null)
    var seciliProfil = MutableLiveData<String>()
    var profiller = MutableLiveData<List<String>>()

    init {
        getProfiller()
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
        }
    }

    fun seciliProfiliGetir() {
        CoroutineScope(Dispatchers.Main).launch {
            seciliProfil.value = profileStore.seciliProfiliGetir()
        }
    }

    fun getProfiller() {
        CoroutineScope(Dispatchers.Main).launch {
            profiller.value = profileStore.tumProfilleriGetir()
            if (profiller.value.isNotEmpty() && seciliProfil.value.isNullOrEmpty()) {
                seciliProfil.value = profiller.value.first()
            }
        }
    }


}