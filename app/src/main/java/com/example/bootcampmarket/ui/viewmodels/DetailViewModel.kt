package com.example.bootcampmarket.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var urunlerRepository : urunlerRepository) : ViewModel() {
    var urunlerList = MutableLiveData<List<Urunler>>()

    init {
        loadUrunler()
    }

    fun sepettenSil(id: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerRepository.sepettenSil(id, kullaniciAdi)
            loadUrunler()
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
            urunlerRepository.sepeteUrunEkle(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
            Toast.makeText(context, "Ürün sepete eklendi", Toast.LENGTH_SHORT).show()
            loadUrunler()
        }
    }

    fun loadUrunler() {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerList.value = urunlerRepository.loadUrunler()
        }
    }

    fun loadUrunById(id: Int, onResult: (Urunler?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val urun = urunlerRepository.loadUrunById(id)
            onResult(urun)
        }
    }

//    fun search(searchText:String) {
//        CoroutineScope(Dispatchers.Main).launch {
//            toDosList.value = toDosRepository.search(searchText)
//        }
//    }
}