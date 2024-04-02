package com.example.quotesappmvvmroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    // singleton pattern
    companion object {
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDatabase::class.java,
                        "quote_database"
                    )
                        // this method will pre-populate the database by the time database gets created..
                        .createFromAsset("quotes.db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}