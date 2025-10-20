package com.example.bootcampmarket.data.repos

import android.util.Log
import com.example.bootcampmarket.data.datasources.UrunlerDatasource
import com.example.bootcampmarket.data.entity.Urunler

class ToDosRepository (var urunlerDatasource : UrunlerDatasource) {
    suspend fun save(name:String,image:String){
        urunlerDatasource.save(name,image)
    }

    suspend fun update(id:Int,name:String) = urunlerDatasource.update(id,name)

    suspend fun delete(id:Int) = urunlerDatasource.delete(id)

    suspend fun loadToDos() : List<Urunler> = urunlerDatasource.loadToDos()

    suspend fun search(searchText:String) : List<Urunler> = urunlerDatasource.search(searchText)
}