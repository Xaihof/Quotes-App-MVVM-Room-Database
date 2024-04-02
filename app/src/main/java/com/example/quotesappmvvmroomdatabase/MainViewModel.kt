package com.example.quotesappmvvmroomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepositiory: QuoteRepositiory) : ViewModel() {

    fun getQuotes(): LiveData<List<Quote>> {
        return quoteRepositiory.getQuotes()
    }

    fun insertQuote(quote: Quote) {

        /*As this function calls a suspend function, it must be a suspend function itself or the suspend function must be called by a coroutine which is in this case a viewModelScope.
        * As it is an Input/Output operation we will use IO Thread (Dispatchers.IO)*/

        viewModelScope.launch(Dispatchers.IO) {
            quoteRepositiory.insertQuote(quote)
        }
    }

}