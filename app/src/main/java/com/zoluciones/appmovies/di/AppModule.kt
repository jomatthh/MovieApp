package com.zoluciones.appmovies.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.zoluciones.appmovies.storage.db.DbMovie
import com.zoluciones.appmovies.storage.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton()
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DbMovie::class.java, "movie.db").build()


    @Singleton()
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): WebService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }

}