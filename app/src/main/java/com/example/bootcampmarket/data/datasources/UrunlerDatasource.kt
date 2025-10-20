package com.example.bootcampmarket.data.datasources

import com.example.bootcampmarket.data.entity.Urunler
import com.example.bootcampmarket.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerDatasource(var urunlerDao: UrunlerDao) {
    suspend fun save(name:String,image:String){
        urunlerDao.save(name,image)
    }
    suspend fun update(id:Int,name:String){
        urunlerDao.update(id,name)
    }
    suspend fun delete(id:Int){
        urunlerDao.delete(id)
    }
    suspend fun loadToDos() : List<Urunler> = withContext(Dispatchers.IO) {
        try{
            return@withContext urunlerDao.loadTumUrunler().toDos
        }catch (e: Exception){
            return@withContext emptyList<Urunler>()
        }
    }
    suspend fun search(searchText:String) : List<Urunler> = withContext(Dispatchers.IO) {
        try{
            return@withContext urunlerDao.search(searchText).toDos
        }catch (e: Exception){
            return@withContext emptyList<Urunler>()
        }
    }
}