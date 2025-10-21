package com.example.bootcampmarket.data.repos

import com.example.bootcampmarket.data.datasources.UrunlerDatasource
import com.example.bootcampmarket.data.entity.SepetUrunleri
import com.example.bootcampmarket.data.entity.Urunler

class urunlerRepository (var urunlerDatasource : UrunlerDatasource) {
    suspend fun sepeteUrunEkle(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ){
        urunlerDatasource.sepeteUrunEkle(ad, resim, kategori, fiyat, marka, siparisAdeti, kullaniciAdi)
    }

    suspend fun sepettenSil(id: Int, kullaniciAdi: String) = urunlerDatasource.sepettenSil(id, kullaniciAdi)

    suspend fun loadUrunler() : List<Urunler> = urunlerDatasource.loadUrunler()


    suspend fun postSepet(kullaniciAdi: String) : List<SepetUrunleri> = urunlerDatasource.postSepet(kullaniciAdi)

    suspend fun adetGuncelle(sepetUrunu: SepetUrunleri, yeniAdet: Int, kullaniciAdi: String) = urunlerDatasource.adetGuncelle(sepetUrunu, yeniAdet, kullaniciAdi)

}