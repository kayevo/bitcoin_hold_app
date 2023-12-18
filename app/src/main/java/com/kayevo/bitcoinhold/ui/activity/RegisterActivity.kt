package com.kayevo.bitcoinhold.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityRegisterBinding
import com.kayevo.bitcoinhold.helper.NotificationHelper
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
                val email = txtEmail.text.toString()
                val password = txtPassword.text.toString()
                if (registerViewModel.isValidForm(email, password)) {
                    registerViewModel.registeredEmail(
                        BuildConfig.BITCOIN_HOLD_API_KEY, txtEmail.text.toString()
                    )
                } else {
                    showMessage(
                        this@RegisterActivity.getString(R.string.register_invalid_form)
                    )
                }
            }
            btnTurnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun setObservers() {
        with(registerView) {
            registerViewModel.registerResult.observe(this@RegisterActivity) { result ->
                when (result) {
                    is RegisterResult.Success -> {
                        showNotification()
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_success_registering)
                        )
                        goToLogin()
                    }
                    else -> {
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_error_registering)
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
                            .register(
                                BuildConfig.BITCOIN_HOLD_API_KEY,
                                txtEmail.text.toString(),
                                txtPassword.text.toString()
                            )
                    }
                    is RegisteredEmailResult.RegisteredEmail -> {
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_registered_email)
                        )
                    }
                    else -> {
                        showMessage(
                            this@RegisterActivity.getString(R.string.register_error_registering)
                        )
                    }
                }
            }
        }
    }

    private fun showNotification() {
        NotificationHelper.createSampleDataNotificationAndOpenLogin(
            this@RegisterActivity, "Você foi registrado",
            "Usuário registrado com sucesso no app da Bitcoin Hold",
            "Usuário registrado com sucesso"
        )
    }

    private fun goToLogin() {
        finish()
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}