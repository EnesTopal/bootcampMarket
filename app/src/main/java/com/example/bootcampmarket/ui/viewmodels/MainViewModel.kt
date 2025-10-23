package com.example.bootcampmarket.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.datasources.ProfileStore
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
class MainViewModel @Inject constructor(
    var urunlerRepository: urunlerRepository,
    var favorilerRepository: FavorilerRepository,
    var profileStore: ProfileStore
) : ViewModel() {
    var urunlerList = MutableLiveData<List<Urunler>>()
    var profiller = MutableLiveData<List<String>>()

    var seciliProfil = MutableLiveData<String>()




    init {
        loadUrunler()
        getProfiller()
    }

    fun addFavoriler(
        favori: FavoriUrunler
    ){
        CoroutineScope(Dispatchers.Main).launch {
            favorilerRepository.save(favori)
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
            Log.d("Profile", "MainVM $kullaniciAdi")
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
            loadUrunler()
        }
    }

    fun loadUrunler() {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerList.value = urunlerRepository.loadUrunler()
        }
    }

    fun favorilerTablosundaVarmi(id: Int, kullaniciAdi: String, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val exists = favorilerRepository.favorilerTablosundaVarmi(id, kullaniciAdi)
            onResult(exists)
        }
    }

    fun saveFavori(favori: FavoriUrunler) {
        CoroutineScope(Dispatchers.Main).launch {
            favorilerRepository.save(favori)
        }
    }

    fun removeFavoriler(id: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            favorilerRepository.delete(id, kullaniciAdi)
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

    fun secilenProfiliAta(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            seciliProfil.value = username
            profileStore.secilenProfiliAta(username)
        }
    }

    fun seciliProfiliGetir() {
        CoroutineScope(Dispatchers.Main).launch {
            seciliProfil.value = profileStore.seciliProfiliGetir()
        }
    }

}