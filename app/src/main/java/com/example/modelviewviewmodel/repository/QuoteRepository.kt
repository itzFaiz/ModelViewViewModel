package com.example.modelviewviewmodel.repository

import androidx.lifecycle.LiveData
import com.example.modelviewviewmodel.roomDatabase.Quote
import com.example.modelviewviewmodel.roomDatabase.QuoteDao

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        quoteDao.insertQuote(quote)
    }
}

