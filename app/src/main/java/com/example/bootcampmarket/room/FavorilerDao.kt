package com.example.bootcampmarket.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bootcampmarket.data.entity.FavoriUrunler

@Dao
interface FavorilerDao {
    @Query("SELECT * FROM favoriler WHERE kullaniciAdi = :kullaniciAdi")
    suspend fun loadFavoriler(kullaniciAdi: String) : List<FavoriUrunler>

    @Insert
    suspend fun save(favoriUrunler: FavoriUrunler)

    @Query("DELETE FROM favoriler WHERE id = :id AND kullaniciAdi = :kullaniciAdi")
    suspend fun delete(id: Int, kullaniciAdi: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favoriler WHERE id = :id AND kullaniciAdi = :kullaniciAdi)")
    suspend fun favoriTablosundaVarmi(id: Int, kullaniciAdi: String): Boolean
}