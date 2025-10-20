package com.example.bootcampmarket.data.entity

data class UrunlerResponse(
    var urunler_sepeti: List<Urunler>,
    var success: Int
) {
}
