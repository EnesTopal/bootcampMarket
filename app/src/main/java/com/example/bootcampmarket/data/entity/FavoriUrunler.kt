package com.example.bootcampmarket.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriler")
data class FavoriUrunler(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "favoriId") val favoriId: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "ad") val ad: String,
    @ColumnInfo(name = "resim") val resim: String,
    @ColumnInfo(name = "kategori") val kategori: String,
    @ColumnInfo(name = "fiyat") val fiyat: Int,
    @ColumnInfo(name = "marka") val marka: String,
    @ColumnInfo(name = "kullaniciAdi") val kullaniciAdi: String
)
