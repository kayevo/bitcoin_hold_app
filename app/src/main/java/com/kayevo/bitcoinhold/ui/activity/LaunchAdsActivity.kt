package com.kayevo.bitcoinhold.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kayevo.bitcoinhold.R
import com.squareup.picasso.Picasso

class LaunchAdsActivity : AppCompatActivity() {
    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_ads)

        // Get from API - Ads Entity: imageUrl and web link

        /*
        val urlImage = "https://i.imgur.com/MGSAzbl.png"
        picasso.load(urlImage).into(portfolioView.loading.imgLoading)
        */
    }
}