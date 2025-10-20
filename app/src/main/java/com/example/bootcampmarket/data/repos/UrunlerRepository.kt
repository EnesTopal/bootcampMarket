package com.example.bootcampmarket.data.repos

import com.example.bootcampmarket.data.datasources.UrunlerDatasource
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

    suspend fun loadUrunById(id: Int) : Urunler? = urunlerDatasource.loadUrunById(id)

    suspend fun getSepet(kullaniciAdi: String) : List<Urunler> = urunlerDatasource.getSepet(kullaniciAdi)

    suspend fun postSepet(kullaniciAdi: String) : List<Urunler> = urunlerDatasource.postSepet(kullaniciAdi)

//    suspend fun search(searchText:String) : List<Urunler> = urunlerDatasource.search(searchText)
}