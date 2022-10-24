package com.kayevo.bitcoinhold.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.databinding.ActivityDonationBinding

class DonationActivity : AppCompatActivity() {
    private lateinit var donationView: ActivityDonationBinding
    private lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donationView = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(donationView.root)
        setListeners()
    }

    private fun setListeners() {
        with(donationView) {
            btnCopyBitcoinAddress.setOnClickListener {
                copyText(txtBitcoinAddress)
            }
            btnCopyLNAddress.setOnClickListener {
                copyText("kayevo@zbd.gg")
            }
            btnTurnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun copyText(editText: TextView) {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("BITCOIN_ADDRESS", text)
            clipboardManager.setPrimaryClip(clipData)
            showMessage("Copiado")
        } else {
            showMessage("Texto vazio")
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