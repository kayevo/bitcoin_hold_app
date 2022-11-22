package com.kayevo.bitcoinhold.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.AdsRepository
import com.kayevo.bitcoinhold.data.result.AdsRepoResult
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.ui.result.AdsResult
import com.kayevo.bitcoinhold.ui.result.LoginResult
import kotlinx.coroutines.launch
import kotlin.random.Random

class AdsViewModel(
    private val repository: AdsRepository
) : ViewModel() {
    private val _adsResult = MutableLiveData<AdsResult>()
    val adsResult: LiveData<AdsResult> get() = _adsResult

    fun getAds() {
        viewModelScope.launch {
            when(val adsResponse = repository.getAllAds()){
                is AdsRepoResult.Success ->{
                    var randomGenerator = Random(System.currentTimeMillis())
                    var adsSelected = randomGenerator.nextInt(0, adsResponse.allAds.size)
                    _adsResult.postValue(AdsResult.Success(adsResponse.allAds[adsSelected]))
                }
                is AdsRepoResult.NotFound ->{
                    _adsResult.postValue(AdsResult.NotFound)
                }
                else->{
                    _adsResult.postValue(AdsResult.Error)
                }
            }
        }
    }
}