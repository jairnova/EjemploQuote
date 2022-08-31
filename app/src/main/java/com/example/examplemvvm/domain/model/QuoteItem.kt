package com.example.examplemvvm.domain.model

import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel

data class QuoteItem (val quote: String, val author:String)

//Función de extención
fun QuoteModel.toDomain() = QuoteItem(quote,author)
fun QuoteEntity.toDomain() = QuoteItem(quote,author)