package com.example.bootcampmarket.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.datasources.ProfileStore
import com.example.bootcampmarket.data.entity.SepetUrunleri
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    var urunlerRepository: urunlerRepository,
    var profileStore: ProfileStore
) : ViewModel() {
    val urunlerList = MutableLiveData<List<SepetUrunleri>>(emptyList())
    var profiller = MutableLiveData<List<String>>()

    var seciliProfil = MutableLiveData<String>()

    init {
        getProfiles()
        seciliProfiliGetir()
//        postSepet(seciliProfil.value)
    }

    fun sepettenSil(id: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerRepository.sepettenSil(id, kullaniciAdi)
            postSepet(kullaniciAdi)
        }
    }


    fun postSepet(kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerList.value = urunlerRepository.postSepet(kullaniciAdi)
        }
    }

    fun adetGuncelle(sepetUrunu: SepetUrunleri, yeniAdet: Int, kullaniciAdi: String) {
        Log.d("CartViewModel", "adetGuncelle called with yeniAdet: $yeniAdet")
        CoroutineScope(Dispatchers.Main).launch {
            urunlerRepository.adetGuncelle(sepetUrunu, yeniAdet, kullaniciAdi)
            postSepet(kullaniciAdi)
        }
    }

    fun getProfiles() {
        CoroutineScope(Dispatchers.Main).launch {
            profiller.value = profileStore.tumProfilleriGetir()
            if (profiller.value.isNotEmpty() && seciliProfil.value.isNullOrEmpty()) {
                seciliProfil.value = profiller.value.first()
                postSepet(seciliProfil.value)
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