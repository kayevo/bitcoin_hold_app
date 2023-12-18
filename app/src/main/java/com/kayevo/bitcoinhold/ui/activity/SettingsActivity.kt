package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.databinding.ActivitySettingsBinding
import com.kayevo.bitcoinhold.ui.fragment.CustomizeAmountFragment


class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsView: ActivitySettingsBinding
    private var customizeAmountModal: CustomizeAmountFragment? = null

    companion object {
        const val KEY_USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsView = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsView.root)

        intent.getStringExtra(PortfolioActivity.KEY_USER_ID)?.let { userId ->
            setListeners(userId)
        }

    }

    private fun setListeners(userId: String) {
        with(settingsView) {
            btnLogout.setOnClickListener {
                goToLogin()
            }
            btnDonation.setOnClickListener {
                goToDonation()
            }
            btnTurnBack.setOnClickListener {
                finish()
            }
            btnCustomizeAmount.setOnClickListener {
                goToCustomizeAmount(userId)
            }
            btnSupport.setOnClickListener {
                goToActivity(SupportActivity::class.java)
            }
        }
    }

    private fun goToCustomizeAmount(userId: String) {
        customizeAmountModal = CustomizeAmountFragment().apply {
            arguments = Bundle().apply {
                putString(CustomizeAmountFragment.KEY_USER_ID, userId)
            }
        }
        customizeAmountModal?.show(
            supportFragmentManager, CustomizeAmountFragment.TAG_CUSTOMIZE_FUNDS
        )
    }

    private fun goToLogin() {
        val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun goToDonation() {
        val intent = Intent(this@SettingsActivity, DonationActivity::class.java)
        startActivity(intent)
    }

    private fun goToSupport() {
        val intent = Intent(this@SettingsActivity, SupportActivity::class.java)
        startActivity(intent)
    }

    private fun <T> goToActivity(className: Class<T>){
        val intent = Intent(this@SettingsActivity, className)
        startActivity(intent)
    }
}