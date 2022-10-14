package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding
import com.kayevo.bitcoinhold.databinding.ActivityRegisterBinding
import com.kayevo.bitcoinhold.ui.result.LoginResult
import com.kayevo.bitcoinhold.ui.result.RegisterResult
import com.kayevo.bitcoinhold.ui.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerView: ActivityRegisterBinding
    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerView = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerView.root)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(registerView) {
            btnRegister.setOnClickListener {
                registerViewModel.register(txtEmail.text.toString(), txtPassword.text.toString())
            }
        }
    }

    private fun setObservers() {
        registerViewModel.registerResult.observe(this) { result ->
            when (result) {
                is RegisterResult.Success -> {
                    goToLogin()
                }
                else -> {
                    showMessage(this.getString(R.string.register_error_register))
                }
            }
        }
    }
    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}