package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityLoginBinding
import com.kayevo.bitcoinhold.ui.result.LoginResult
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
        goToAds()
    }

    private fun setListeners() {
        with(loginView) {
            btnLogin.setOnClickListener {
                loginViewModel.login(
                    BuildConfig.BITCOIN_HOLD_API_KEY,
                    txtEmail.text.toString(), txtPassword.text.toString()
                )
            }
            txtRegisterAccount.setOnClickListener {
                goToRegister()
            }
        }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun setObservers() {
        loginViewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    showMessage(this.getString(R.string.login_success_logging))
                    goToPortfolio(result.userId)
                }
                is LoginResult.NotFound -> {
                    showMessage(this.getString(R.string.login_error_not_found_user))
                }
                else -> {
                    showMessage(this.getString(R.string.login_error_logging))
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToPortfolio(userId: String) {
        val intent = Intent(this, PortfolioActivity::class.java)
        intent.putExtra(PortfolioActivity.KEY_USER_ID, userId)
        startActivity(intent)
    }

    private fun goToAds() {
        val intent = Intent(this, AdsActivity::class.java)
        startActivity(intent)
    }

}