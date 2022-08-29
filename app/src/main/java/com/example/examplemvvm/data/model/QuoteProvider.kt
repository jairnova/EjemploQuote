package com.example.examplemvvm.data.model

class QuoteProvider {

    // seria como una clase estatica en java
    companion object {

        var quotes:List<QuoteModel> = emptyList()
    }
}