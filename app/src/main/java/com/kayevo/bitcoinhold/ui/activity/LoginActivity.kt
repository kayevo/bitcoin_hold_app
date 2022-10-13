package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityLoginBinding
import com.kayevo.bitcoinhold.ui.result.LoginViewResult
import com.kayevo.bitcoinhold.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginView: ActivityLoginBinding
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginView = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginView.root)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        loginViewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginViewResult.Success -> {
                    val intent = Intent(this, PortfolioActivity::class.java)
                    intent.putExtra(PortfolioActivity.KEY_USER_ID, )
                    startActivity(intent)
                }
                else ->{
                    Toast.makeText(
                        this, this.getString(R.string.login_error_logging),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setListeners() {
        loginView.btnLogin.setOnClickListener {
            loginViewModel
        }
    }
}