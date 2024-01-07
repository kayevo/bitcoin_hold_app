package com.kayevo.bitcoinhold.data.service

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kayevo.bitcoinhold.helper.NotificationHelper

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class AppFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            NotificationHelper.createPushNotification(
                this,
                "${it.title}",
                "${it.body}")
        }
    }
}