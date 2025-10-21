package com.example.bootcampmarket.data.datasources

import android.util.Log
import com.example.bootcampmarket.data.entity.SepetUrunleri
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
        val sepetCheck = postSepet(kullaniciAdi)
        sepetCheck.find { it.ad == ad }?.let { urun ->
            val yeniAdet = urun.siparisAdeti + siparisAdeti
            adetGuncelle(urun, yeniAdet, kullaniciAdi)
            return
        }
        urunlerDao.sepeteUrunEkle(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
    }


    suspend fun sepettenSil(id: Int, kullaniciAdi: String) {
        urunlerDao.sepettenSil(id, kullaniciAdi)
    }

    suspend fun loadUrunler(): List<Urunler> = withContext(Dispatchers.IO) {
        try {
            Log.d("UrunlerDatasource", "loadUrunler called")
            return@withContext urunlerDao.loadTumUrunler().urunler
        } catch (e: Exception) {
            return@withContext emptyList<Urunler>()
        }
    }


    suspend fun postSepet(kullaniciAdi: String): List<SepetUrunleri> = withContext(Dispatchers.IO) {
        try {
            return@withContext urunlerDao.postSepet(kullaniciAdi).urunler_sepeti
        } catch (e: Exception) {
            return@withContext emptyList<SepetUrunleri>()
        }
    }

    suspend fun adetGuncelle(sepetUrunu: SepetUrunleri, yeniAdet: Int, kullaniciAdi: String) {
        urunlerDao.sepettenSil(sepetUrunu.sepetId, kullaniciAdi)
        urunlerDao.sepeteUrunEkle(
            sepetUrunu.ad,
            sepetUrunu.resim,
            sepetUrunu.kategori,
            sepetUrunu.fiyat,
            sepetUrunu.marka,
            yeniAdet,
            kullaniciAdi
        )
    }
}