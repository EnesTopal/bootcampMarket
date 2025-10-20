package com.example.bootcampmarket.data.datasources

import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerDatasource(var urunlerDao: UrunlerDao) {
    suspend fun sepeteUrunEkle(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ) {
        urunlerDao.sepeteUrunEkle(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
    }


    suspend fun sepettenSil(id: Int, kullaniciAdi: String) {
        urunlerDao.sepettenSil(id, kullaniciAdi)
    }

    suspend fun loadUrunler(): List<Urunler> = withContext(Dispatchers.IO) {
        try {
            return@withContext urunlerDao.loadTumUrunler().urunler_sepeti
        } catch (e: Exception) {
            return@withContext emptyList<Urunler>()
        }
    }

    suspend fun loadUrunById(id: Int): Urunler? = withContext(Dispatchers.IO) {
        try {
            return@withContext urunlerDao.loadUrunById(id)
        }
        catch (e: Exception) {
            return@withContext Urunler(0,"","","",0,"")
        }
    }

    suspend fun getSepet(kullaniciAdi: String): List<Urunler> = withContext(Dispatchers.IO) {
        try {
            return@withContext urunlerDao.getSepet(kullaniciAdi).urunler_sepeti
        } catch (e: Exception) {
            return@withContext emptyList<Urunler>()
        }
    }

    suspend fun postSepet(kullaniciAdi: String): List<Urunler> = withContext(Dispatchers.IO) {
        try {
            return@withContext urunlerDao.postSepet(kullaniciAdi).urunler_sepeti
        } catch (e: Exception) {
            return@withContext emptyList<Urunler>()
        }
    }

//    suspend fun search(searchText: String): List<Urunler> = withContext(Dispatchers.IO) {
//        try {
//            return@withContext urunlerDao.search(searchText).toDos
//        } catch (e: Exception) {
//            return@withContext emptyList<Urunler>()
//        }
//    }
}