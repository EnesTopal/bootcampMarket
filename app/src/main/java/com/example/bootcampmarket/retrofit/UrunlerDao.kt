package com.example.bootcampmarket.retrofit

import android.util.Log
import com.example.bootcampmarket.data.entity.CRUDResponse
import com.example.bootcampmarket.data.entity.SepetResponse
import com.example.bootcampmarket.data.entity.SepetUrunleri
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.data.entity.UrunlerResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UrunlerDao {
    @GET("tumUrunleriGetir.php")
    suspend fun loadTumUrunler(): UrunlerResponse


    @FormUrlEncoded
    @POST("sepettekiUrunleriGetir.php")
    suspend fun postSepet(@Field("kullaniciAdi") kullaniciAdi: String): SepetResponse

    @POST("sepeteUrunEkle.php")
    @FormUrlEncoded
    suspend fun sepeteUrunEkle(
        @Field("ad") ad: String,
        @Field("resim") resim: String,
        @Field("kategori") kategori: String,
        @Field("fiyat") fiyat: Int,
        @Field("marka") marka: String,
        @Field("siparisAdeti") siparisAdeti: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CRUDResponse

    @POST("sepettenUrunSil.php")
    @FormUrlEncoded
    suspend fun sepettenSil(
        @Field("sepetId") id: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CRUDResponse

}