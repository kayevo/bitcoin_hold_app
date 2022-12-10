package com.kayevo.bitcoinhold.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivitySettingsBinding
import com.kayevo.bitcoinhold.databinding.ActivitySupportBinding

class SupportActivity : AppCompatActivity() {
    private lateinit var supportView: ActivitySupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportView = ActivitySupportBinding.inflate(layoutInflater)
        setContentView(supportView.root)

        setListeners()
    }

    private fun setListeners() {
        with(supportView) {
            btnTurnBack.setOnClickListener {
                finish()
            }
        }
    }
}