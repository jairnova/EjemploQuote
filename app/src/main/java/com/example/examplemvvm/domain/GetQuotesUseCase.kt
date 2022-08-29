package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel

class GetQuotesUseCase {

// No es necesario colocar "UseCase" en el nombre ya que es solo par que se recuerde que esta
// clase se en carga de un caso de uso que seria la lógica de negocio

    private val repository = QuoteRepository()

    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

}