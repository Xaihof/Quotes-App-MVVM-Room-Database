package com.example.quotesappmvvmroomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotesappmvvmroomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repositiory = QuoteRepositiory(dao)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repositiory)
        ).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })

        binding.buttonAddQuote.setOnClickListener {

            val quote = Quote(0, "This is a testing Quote.", "This is a testing Author.")
            mainViewModel.insertQuote(quote)
        }
    }
}