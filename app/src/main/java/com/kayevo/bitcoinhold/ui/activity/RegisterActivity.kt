package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityRegisterBinding
import com.kayevo.bitcoinhold.ui.result.RegisterResult
import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult
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
                registerViewModel.registeredEmail(txtEmail.text.toString())
            }
        }
    }

    private fun setObservers() {
        with(registerView) {
            registerViewModel.registerResult.observe(this@RegisterActivity) { result ->
                when (result) {
                    is RegisterResult.Success -> {
                        goToLogin()
                    }
                    else -> {
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_error_register)
                        )
                    }
                }
            }

            registerViewModel.registeredEmailResult.observe(
                this@RegisterActivity
            ) { registeredEmailResult ->
                when (registeredEmailResult) {
                    is RegisteredEmailResult.NotRegisteredEmail -> {
                        registerViewModel
                            .register(txtEmail.text.toString(), txtPassword.text.toString())
                    }
                    is RegisteredEmailResult.RegisteredEmail -> {
                        this@RegisterActivity.getString(R.string.register_registered_email)
                    }
                    else -> {
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_error_register)
                        )
                    }
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