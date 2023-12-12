package com.kayevo.bitcoinhold.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kayevo.bitcoinhold.databinding.ActivitySupportBinding
import com.kayevo.bitcoinhold.BuildConfig

class SupportActivity : AppCompatActivity() {
    private lateinit var supportView: ActivitySupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportView = ActivitySupportBinding.inflate(layoutInflater)
        setContentView(supportView.root)
        setSupportEmail()
        setListeners()
    }

    private fun setSupportEmail(){
        supportView.txtSupportEmail.text = BuildConfig.SUPPORT_EMAIL
    }

    private fun setListeners() {
        with(supportView) {
            btnTurnBack.setOnClickListener {
                finish()
            }
        }
    }
}