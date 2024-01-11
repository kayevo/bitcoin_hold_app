package com.kayevo.bitcoinhold.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.network.response.AdsResponse
import com.kayevo.bitcoinhold.databinding.ActivityAdsBinding
import com.kayevo.bitcoinhold.ui.result.AdsResult
import com.kayevo.bitcoinhold.ui.viewmodel.AdsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.kayevo.bitcoinhold.helper.NotificationHelper


class AdsActivity : AppCompatActivity() {
    private lateinit var adsView: ActivityAdsBinding
    private val adsViewModel by viewModel<AdsViewModel>()
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
            return@registerForActivityResult
        } else {
            // TODO: Inform user that that your app will not show notifications.
            return@registerForActivityResult
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adsView = ActivityAdsBinding.inflate(layoutInflater)
        setContentView(adsView.root)

        setObservers()
        showLoading()

        adsViewModel.getAds(BuildConfig.BITCOIN_HOLD_API_KEY)
        askNotificationPermission()
    }

    private fun showLoading() {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.menu_background_shape)
            .error(R.color.green)

        Glide.with(this).load(R.drawable.loading_gif).apply(options).into(
            adsView.loading.imgLoading
        )
    }

    private fun setListeners(adsWebsiteLink: String) {
        with(adsView) {
            btnClose.setOnClickListener {
                goToLogin()
            }
            btnAdsWebsite.setOnClickListener {
                goToAdsWebsite(adsWebsiteLink)
            }
        }
    }

    private fun goToAdsWebsite(adsWebsiteLink: String) {
        openNewTabWindow(adsWebsiteLink, this)
    }

    private fun openNewTabWindow(urls: String, context: Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

    private fun goToLogin() {
        finish()
    }

    private fun setObservers() {
        adsViewModel.adsResult.observe(this) { result ->
            when (result) {
                is AdsResult.Success -> {
                    showAds(result.ads)
                    setListeners(result.ads.websiteLink)
                }

                is AdsResult.NotFound -> {
                    goToLogin()
                    Log.d("Server error", "Ads not found")
                }

                else -> {
                    goToLogin()
                    Log.d("Server error", "Server errors when calling Ads")
                }
            }
        }
    }

    private fun showAds(ads: AdsResponse) {
        with(adsView) {
            adsLoading.visibility = View.GONE
            adsLayout.visibility = View.VISIBLE
        }

        val options: RequestOptions = RequestOptions()
            .fitCenter()
            .placeholder(R.drawable.menu_background_shape)
            .error(R.color.green)

        Glide.with(this).load(ads.posterUrl).apply(options).into(adsView.imgAds)
    }

    private fun getFirebaseMessagingDeviceToken() {
        NotificationHelper.getFirebaseMessagingDeviceToken()
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // For API level >= 33 (TIRAMISU)
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return
            } else if (shouldShowRequestPermissionRationale(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                return
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}