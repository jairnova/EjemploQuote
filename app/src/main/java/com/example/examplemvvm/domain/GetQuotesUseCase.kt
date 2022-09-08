package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.domain.model.QuoteItem
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
){
// No es necesario colocar "UseCase" en el nombre de la clase ya que es solo para recordar que esta
// clase se encarga de un caso de uso, que seria la lógica de negocio

    //Si creo una intancia de esta clase automaticamente estaria llamando al método invoke ejecutando
    // lo que este dentro del método asiendolo mas pro, el siempre retornar lo que esta pidiendo el caso de uso
    suspend operator fun invoke():List<QuoteItem> {

        val quotes = repository.getAllQuotesFromApi()

       return if(quotes.isNotEmpty()){
           repository.clearQuotes()
           repository.insertQuotes(quotes.map { it.toDatabase() })
           quotes
        } else {
            repository.getAllQuotesFromDataBase()
        }
    }
}