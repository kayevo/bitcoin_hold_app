package com.kayevo.bitcoinhold.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.databinding.ActivityDonationBinding
import com.kayevo.bitcoinhold.BuildConfig

class DonationActivity : AppCompatActivity() {
    private lateinit var donationView: ActivityDonationBinding
    private lateinit var clipboardManager: ClipboardManager
    private val lNBitcoinAddress: String = BuildConfig.BITCOIN_LN_ADDRESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donationView = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(donationView.root)
        setBitcoinAddress()
        setListeners()
    }

    private fun setBitcoinAddress(){
        donationView.txtBitcoinAddress.text = BuildConfig.BITCOIN_ADDRESS
    }

    private fun setListeners() {
        with(donationView) {
            btnCopyBitcoinAddress.setOnClickListener {
                copyText(txtBitcoinAddress.text.toString())
            }
            btnCopyLNAddress.setOnClickListener {
                copyText(lNBitcoinAddress)
            }
            btnTurnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun copyText(text: String) {
        if (text.isNotEmpty()) {
            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("BITCOIN_ADDRESS", text)
            clipboardManager.setPrimaryClip(clipData)
            showMessage("Copiado")
        } else {
            showMessage("Texto vazio")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}