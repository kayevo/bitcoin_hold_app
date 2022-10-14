package com.kayevo.bitcoinhold.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding
import com.kayevo.bitcoinhold.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerView: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerView = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerView.root)
    }
}