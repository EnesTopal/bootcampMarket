package com.example.bootcampmarket.data.datasources


import com.example.bootcampmarket.data.entity.FavoriUrunler
import com.example.bootcampmarket.room.FavorilerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavorilerDatasource(var favorilerDao: FavorilerDao) {
    suspend fun save(favori: FavoriUrunler) {
        favorilerDao.save(favori)
    }

    suspend fun delete(id: Int, kullaniciAdi: String) = withContext(Dispatchers.IO) {
        favorilerDao.delete(id, kullaniciAdi)
    }

    suspend fun loadFavoriler(kullaniciAdi: String): List<FavoriUrunler> = withContext(Dispatchers.IO) {
        return@withContext favorilerDao.loadFavoriler(kullaniciAdi)
    }

    suspend fun favoriTablosundaVarmi(id: Int, kullaniciAdi: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext favorilerDao.favoriTablosundaVarmi(id, kullaniciAdi)
    }
}