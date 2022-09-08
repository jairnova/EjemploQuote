package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest{

    //Esto es lo que mokeamos ya que se truncaran las respuestas, se usa la tarjeta "" esta nos
    // ayuda si nosotros no definimos una de las respuestas de la clase que estamos creando nos la genera el automaticamente
    @RelaxedMockK
    private lateinit var quoteRepository : QuoteRepository

    //Siempre creamos una insatacia de la clase a testear
    //Se crea una instancia real del caso de uso que estamos probando
    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    // CREANDO LOS TEST

    //las funciones que se crean para el text llevan la etiqueta "@Test" las tildes o comillas simples
    // al reves nos permiten colocar espacios al nombre de una funcion y se iguala a "runBlocking" por que lanzaremos corrutinas
    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {

        //Given, se da un repositorio mokeado siempre que tengamos una corrutina delante se coloca
        // coEvery si no esta en una corrutina colocamos every
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        //When, cuando tiene que ocurrir esto, pues cuando llamemos a nuestro caso de uso
        getQuotesUseCase()

        //Then, que tiene que pasar cuando se llame a When, lo que vamos a testear, se usa coVerify por que una corrutina
        //Se puede colocar "exactly = 1" para verificar que se a llamado una unica vez en nuestro caso
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDataBase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {

        //Given
        val quoteList = listOf(QuoteItem("SUSCRIBETE YA","AristiDevs"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns quoteList

        //When, estamos comprobando que retorne el valor que le esta dando el api asi que se
        // almacena la respuesta del caso de uso para comprobar que la respuesta es igual a la lista "quoteList"
        val response = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        //comprovamos que no pase por este lado en este caso el else
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDataBase() }

        //comprueva la respuesta
        assert(quoteList == response)
    }

}