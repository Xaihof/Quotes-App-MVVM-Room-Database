package com.example.quotesappmvvmroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Query("SELECT * FROM Quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)
}