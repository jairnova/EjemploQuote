package com.example.examplemvvm.di

import com.example.examplemvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Este modulo nos proveerá dependencias que no sean tan fáciles de proveer como dependencias de
// librerías o de clases que contienen interfaces

@Module                                // Cuando creamos un modulo le podemos decir el alcance que queremos que tenga en @InstallIn()
@InstallIn(SingletonComponent::class)  // en este caso será un alcance a nivel de aplicación
object NetworkModule {

    // proveo Retrofit
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):QuoteApiClient{
        return retrofit.create(QuoteApiClient::class.java)
    }

}