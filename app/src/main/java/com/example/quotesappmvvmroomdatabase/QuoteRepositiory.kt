package com.example.quotesappmvvmroomdatabase

import androidx.lifecycle.LiveData

class QuoteRepositiory(private val quoteDao: QuoteDao) {

    fun getQuotes(): LiveData<List<Quote>> {
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }

}