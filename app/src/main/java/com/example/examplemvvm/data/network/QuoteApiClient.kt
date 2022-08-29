package com.example.examplemvvm.data.network

import com.example.examplemvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    //es una función suspendida porque se usan corrutinas
    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}