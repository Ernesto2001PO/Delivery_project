package com.example.delivery_project.repositories

import android.content.Context
import com.example.delivery_project.models.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {


    fun getRetrofitInstance(context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()


        return Retrofit.Builder()
            .baseUrl("https://proyectodelivery.jmacboy.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}