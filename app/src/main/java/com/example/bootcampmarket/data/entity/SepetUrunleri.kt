package com.example.bootcampmarket.data.entity

data class SepetUrunleri(
    val sepetId: Int,
    val ad: String,
    val resim: String,
    val kategori: String,
    val fiyat: Int,
    val marka: String,
    val siparisAdeti: Int,
    val kullaniciAdi: String
)
