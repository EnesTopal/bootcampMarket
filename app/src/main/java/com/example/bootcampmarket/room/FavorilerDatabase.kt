package com.example.bootcampmarket.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcampmarket.data.entity.FavoriUrunler

@Database(entities = [FavoriUrunler::class], version = 1)
abstract class FavorilerDatabase : RoomDatabase() {
    abstract fun getFavorilerDao() : FavorilerDao
}