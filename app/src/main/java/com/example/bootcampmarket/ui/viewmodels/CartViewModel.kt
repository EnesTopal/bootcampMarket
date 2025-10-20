package com.example.bootcampmarket.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.entity.SepetUrunleri
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var urunlerRepository : urunlerRepository) : ViewModel() {
    val urunlerList = MutableLiveData<List<SepetUrunleri>>(emptyList())

    init {
        postSepet("enes_topal")
        Log.d("CartViewModel", "${urunlerList.value}")
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

}