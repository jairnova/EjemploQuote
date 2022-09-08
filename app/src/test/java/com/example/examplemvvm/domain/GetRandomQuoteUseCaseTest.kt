package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRandomQuoteUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository : QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empity then return null`() = runBlocking{
        //Given
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns emptyList()

        //When
        val response = getRandomQuoteUseCase()

        //Then
        assert(response == null)
    }

    @Test
    fun `whendatabase is not empity then return quote`() = runBlocking{
        //Given
        val quoteList = listOf(QuoteItem("SUSCRIBETE YA","AristiDevs"))
        coEvery { quoteRepository.getAllQuotesFromDataBase() } returns quoteList

        //When
        val response = getRandomQuoteUseCase()

        //Then, el "first()" me toma  el primer objeto de la vista
        assert(response == quoteList.first())
    }



}