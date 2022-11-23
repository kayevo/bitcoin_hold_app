package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.model.PortfolioAnalysis
import com.kayevo.bitcoinhold.ui.fragment.AddFundsFragment
import com.kayevo.bitcoinhold.ui.fragment.RemoveFundsFragment
import com.kayevo.bitcoinhold.ui.result.PortfolioAnalysisResult
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PortfolioActivity : AppCompatActivity() {
    private lateinit var portfolioView: ActivityPortfolioBinding
    private val portfolioViewModel by viewModel<PortfolioViewModel>()
    private var addFundsModal: AddFundsFragment? = null
    private var removeFundsModal: RemoveFundsFragment? = null

    companion object {
        const val KEY_USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portfolioView = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(portfolioView.root)

        showLoading()

        intent.getStringExtra(KEY_USER_ID)?.let { userId ->
            setListeners(userId)
            setObservers()

            getPortfolio(userId)
        }
    }

    private fun showLoading() {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.menu_background_shape)
            .error(R.color.green)

        Glide.with(this).load(R.drawable.loading_gif).apply(options).into(
            portfolioView.loading.imgLoading
        )
    }

    private fun getPortfolio(userId: String) {
        portfolioViewModel.getPortfolio(userId)
    }

    private fun setObservers() {
        portfolioViewModel.portfolioResult.observe(this) { result ->
            when (result) {
                is PortfolioResult.Success -> {
                    showPortfolio(result.portfolio)
                    getPortfolioAnalysis(result.portfolio)
                }
                else -> {
                    showMessage(this.getString(R.string.portfolio_error_get_portfolio))
                    goToLogin()
                }
            }
        }

        portfolioViewModel.portfolioAnalysisResult.observe(this) { result ->
            when (result) {
                is PortfolioAnalysisResult.Success -> {

                    showPortfolioAnalysis(result.portfolioAnalysis)
                    removeLoading()
                }
                else -> {
                    showMessage(this.getString(R.string.portfolio_error_get_portfolio_analysis))
                    goToLogin()
                }
            }
        }
    }

    private fun removeLoading() {
        with(portfolioView) {
            portfolio.visibility = View.VISIBLE
            portfolioLoading.visibility = View.GONE
        }
    }

    private fun showPortfolioAnalysis(portfolioAnalysis: PortfolioAnalysis) {
        with(portfolioView) {
            txtBitcoinPrice.text = portfolioAnalysis.bitcoinPriceInFiat
            txtBitcoinAmountValue.text = portfolioAnalysis.satoshiAmountValue
            txtProfitPercentage.text = portfolioAnalysis.profitPercentage
        }
    }

    private fun getPortfolioAnalysis(portfolio: Portfolio) {
        portfolioViewModel.getPortfolioAnalysis(portfolio)
    }

    private fun showPortfolio(portfolio: Portfolio) {
        with(portfolioView) {
            txtBitcoinAmount.text = portfolio.bitcoinAmount
            txtAveragePrice.text = portfolio.bitcoinAveragePrice
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setListeners(userId: String) {
        with(portfolioView) {
            menu.btnAddFunds.setOnClickListener {
                goToAddFunds(userId)
            }
            menu.btnRemoveFunds.setOnClickListener {
                goToRemoveFunds(userId)
            }
            menu.btnSettings.setOnClickListener {
                goToSettings(userId)
            }
        }
    }

    private fun goToLogin() {
        finish()
    }

    private fun goToSettings(userId: String) {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.KEY_USER_ID, userId)
        startActivity(intent)
    }

    private fun goToRemoveFunds(userId: String) {
        removeFundsModal = RemoveFundsFragment().apply {
            arguments = Bundle().apply {
                putString(RemoveFundsFragment.KEY_USER_ID, userId)
            }
        }
        removeFundsModal?.show(supportFragmentManager, RemoveFundsFragment.TAG_REMOVE_FUNDS)
    }

    private fun goToAddFunds(userId: String) {
        addFundsModal = AddFundsFragment().apply {
            arguments = Bundle().apply {
                putString(AddFundsFragment.KEY_USER_ID, userId)
            }
        }
        addFundsModal?.show(supportFragmentManager, AddFundsFragment.TAG_ADD_FUNDS)
    }
}