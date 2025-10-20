package com.example.bootcampmarket.retrofit

import com.example.bootcampmarket.data.entity.CRUDResponse
import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.data.entity.UrunlerResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UrunlerDao {
    //http://kasimadalan.pe.hu/toDos/getAllToDos.php
    //http://kasimadalan.pe.hu/ -> BASE URL
    //toDos/getAllToDos.php -> API URL
    @GET("tumUrunleriGetir.php")
    suspend fun loadTumUrunler(): UrunlerResponse

    suspend fun loadUrunById(id: Int): Urunler? {
        val response = loadTumUrunler()
        return response.urunler_sepeti.firstOrNull { it.id == id }
    }

    @GET("urunler/sepettekiUrunleriGetir.php")
    suspend fun getSepet(@Query("kullaniciAdi") kullaniciAdi: String): UrunlerResponse

    @FormUrlEncoded
    @POST("urunler/sepettekiUrunleriGetir.php")
    suspend fun postSepet(@Field("kullaniciAdi") kullaniciAdi: String): UrunlerResponse

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

//    @POST("toDos/search.php")
//    @FormUrlEncoded
//    suspend fun search(@Field("name") searchText:String) : UrunlerResponse
}