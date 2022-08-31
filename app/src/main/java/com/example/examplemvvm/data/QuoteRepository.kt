package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.QuoteItem
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api:QuoteService,
    private val quoteDao: QuoteDao
){

    suspend fun getAllQuotesFromApi():List<QuoteItem>{
        val response : List<QuoteModel> = api.getQuotes()
        // toma el listado de QuoteModel lo recorre y lo trasforma a un listado de Quote, mapeando la información
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDataBase():List<QuoteItem>{
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }

}