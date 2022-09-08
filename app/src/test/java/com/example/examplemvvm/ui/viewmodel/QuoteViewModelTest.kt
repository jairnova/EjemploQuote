package com.example.examplemvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    //Creamos una regla es una función en el onBefore abstraída lo que permite su reutilización entre otras ventajas
    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase,getRandomQuoteUseCase)

        // Dispacher son los que se encargan de gestionar los hilos que usaran nuestras corutinas
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest{

        //Given
        val quoteList = listOf(QuoteItem("SUSCRIBETE YA","AristiDevs"), QuoteItem("HOLI ","AristiDevs"))
        coEvery {getQuotesUseCase()} returns quoteList

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.quoteItemModel.value == quoteList.first())
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {

        //Given
        val quote = QuoteItem("HOLI ","AristiDevs")
        coEvery {getRandomQuoteUseCase()} returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteItemModel.value == quote)
    }

    @Test
    fun `when randomQuoteUseCase return null keep the last value`() = runTest {

        //Given
        val quote = QuoteItem("HOLI ","AristiDevs")
        quoteViewModel.quoteItemModel.value = quote
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteItemModel.value == quote)
    }
}