package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
){

// No es necesario colocar "UseCase" en el nombre ya que es solo par que se recuerde que esta
// clase se en carga de un caso de uso que seria la lógica de negocio

    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

}