package com.example.bootcampmarket.di

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.bootcampmarket.data.datasources.FavorilerDatasource
import com.example.bootcampmarket.data.datasources.UrunlerDatasource
import com.example.bootcampmarket.data.repos.FavorilerRepository
import com.example.bootcampmarket.data.repos.urunlerRepository
import com.example.bootcampmarket.retrofit.UrunlerDao
import com.example.bootcampmarket.room.FavorilerDao
import com.example.bootcampmarket.room.FavorilerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUrunlerRepository(urunlerDatasource: UrunlerDatasource): urunlerRepository {
        return urunlerRepository(urunlerDatasource)
    }

    @Provides
    @Singleton
    fun provideUrunlerDatasource(urunlerDao: UrunlerDao): UrunlerDatasource {
        return UrunlerDatasource(urunlerDao)
    }

    @Provides
    @Singleton
    fun provideUrunlerDao(retrofit: Retrofit): UrunlerDao {
        return retrofit.create(UrunlerDao::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://kasimadalan.pe.hu/urunler/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFavorilerRepository(favorilerDatasource: FavorilerDatasource): FavorilerRepository {
        return FavorilerRepository(favorilerDatasource)
    }

    @Provides
    @Singleton
    fun provideFavorilerDatasource(favorilerDao: FavorilerDao): FavorilerDatasource {
        return FavorilerDatasource(favorilerDao)
    }

    @Provides
    @Singleton
    fun provideFavorilerDao(@ApplicationContext context: Context): FavorilerDao {
        val db = Room.databaseBuilder(context, FavorilerDatabase::class.java, "favoriler.sqlite")
            .build()
        return db.getFavorilerDao()
    }
}