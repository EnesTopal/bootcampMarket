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
class CartViewModel @Inject constructor(var urunlerRepository : urunlerRepository, sepetKullanici: String) : ViewModel() {
    var urunlerList = MutableLiveData<List<Urunler>>()

    init {
        getSepet(sepetKullanici)
    }

    fun sepettenSil(id: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerRepository.sepettenSil(id, kullaniciAdi)
            getSepet(kullaniciAdi)
        }
    }

    fun getSepet(kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerList.value = urunlerRepository.getSepet(kullaniciAdi)
        }
    }

    fun postSepet(kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            urunlerList.value = urunlerRepository.postSepet(kullaniciAdi)
        }
    }

//    fun search(searchText:String) {
//        CoroutineScope(Dispatchers.Main).launch {
//            toDosList.value = toDosRepository.search(searchText)
//        }
//    }
}