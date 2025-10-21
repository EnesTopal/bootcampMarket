package com.example.bootcampmarket.data.repos

import android.util.Log
import com.example.bootcampmarket.data.datasources.FavorilerDatasource
import com.example.bootcampmarket.data.entity.FavoriUrunler

class FavorilerRepository (var favorilerDatasource : FavorilerDatasource) {
    suspend fun save(favori: FavoriUrunler){
        favorilerDatasource.save(favori)
    }

    suspend fun delete(id: Int, kullaniciAdi: String) = favorilerDatasource.delete(id, kullaniciAdi)

    suspend fun loadFavoriler(kullaniciAdi: String) : List<FavoriUrunler> = favorilerDatasource.loadFavoriler(kullaniciAdi)

    suspend fun favorilerTablosundaVarmi(id: Int, kullaniciAdi: String): Boolean {
        return favorilerDatasource.favoriTablosundaVarmi(id, kullaniciAdi)
    }
}