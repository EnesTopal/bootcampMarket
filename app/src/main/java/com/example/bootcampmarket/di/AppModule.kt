package com.example.bootcampmarket.di

import com.example.bootcampmarket.data.datasources.UrunlerDatasource
import com.example.bootcampmarket.data.repos.ToDosRepository
import com.example.bootcampmarket.retrofit.UrunlerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUrunlerRepository(urunlerDatasource: UrunlerDatasource) : ToDosRepository {
        return ToDosRepository(urunlerDatasource)
    }

    @Provides
    @Singleton
    fun provideUrunlerDatasource(urunlerDao: UrunlerDao) : UrunlerDatasource {
        return UrunlerDatasource(urunlerDao)
    }

    @Provides
    @Singleton
    fun provideUrunlerDao(retrofit: Retrofit) : UrunlerDao {
        return retrofit.create(UrunlerDao::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://kasimadalan.pe.hu/urunler/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}