package com.kayevo.bitcoinhold.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityLoginBinding
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding

class PortfolioActivity : AppCompatActivity() {
    private lateinit var portfolioView: ActivityPortfolioBinding

    companion object{
        const val KEY_USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portfolioView = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(portfolioView.root)
    }
}