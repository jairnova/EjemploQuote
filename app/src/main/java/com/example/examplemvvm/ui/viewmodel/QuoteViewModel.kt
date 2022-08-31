package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.QuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// "QuoteViewModel" la clase donde se va a inyectar este preparada, y la clase que vamos a inyectar
// "GetQuotesUseCase" este preparada

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase : GetQuotesUseCase,
    private val getRandomQuotesUseCase : GetRandomQuoteUseCase
): ViewModel() {

    val quoteItemModel = MutableLiveData<QuoteItem>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()) {
                quoteItemModel.postValue(result[0])
                isLoading.postValue(false)
            } else{
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val quoteItem: QuoteItem? = getRandomQuotesUseCase()
            if(quoteItem != null){
                quoteItemModel.postValue(quoteItem!!)
            }
            isLoading.postValue(false)
        }
    }

}