package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.databinding.ActivitySettingsBinding
import com.kayevo.bitcoinhold.ui.fragment.CustomizeFundsFragment
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsView: ActivitySettingsBinding
    private var customizeFundsModal: CustomizeFundsFragment? = null

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
            btnCustomizeFunds.setOnClickListener {
                goToCustomizeFunds(userId)
            }
        }
    }

    private fun goToCustomizeFunds(userId: String) {
        customizeFundsModal = CustomizeFundsFragment().apply {
            arguments = Bundle().apply {
                putString(CustomizeFundsFragment.KEY_USER_ID, userId)
            }
        }
        customizeFundsModal?.show(
            supportFragmentManager, CustomizeFundsFragment.TAG_CUSTOMIZE_FUNDS
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
}