package com.kayevo.bitcoinhold.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.data.entity.AnalysisEntity
import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding
import com.kayevo.bitcoinhold.helper.parseSatoshiToBitcoin
import com.kayevo.bitcoinhold.helper.parseToCurrency
import com.kayevo.bitcoinhold.helper.parseToPercentage
import com.kayevo.bitcoinhold.ui.fragment.AddAmountFragment
import com.kayevo.bitcoinhold.ui.fragment.RemoveAmountFragment
import com.kayevo.bitcoinhold.ui.result.AnalysisResult
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PortfolioActivity : AppCompatActivity() {
    private lateinit var view: ActivityPortfolioBinding
    private val viewModel by viewModel<PortfolioViewModel>()
    private var addAmountModal: AddAmountFragment? = null
    private var removeAmountModal: RemoveAmountFragment? = null

    companion object {
        const val KEY_USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val mockUserId = BuildConfig.MOCK_USER_ID

        super.onCreate(savedInstanceState)
        view = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(view.root)

        showLoading()

        intent.getStringExtra(KEY_USER_ID)?.let { userId ->
            setListeners(userId)
            setObservers()

            getPortfolio(BuildConfig.BITCOIN_HOLD_API_KEY, userId)
            getAnalysis(BuildConfig.BITCOIN_HOLD_API_KEY, userId)
        }?: run {
            setListeners(mockUserId)
            setObservers()

            getPortfolio(BuildConfig.BITCOIN_HOLD_API_KEY, mockUserId)
            getAnalysis(BuildConfig.BITCOIN_HOLD_API_KEY, mockUserId)
        }
    }

    private fun showLoading() {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.menu_background_shape)
            .error(R.color.green)

        Glide.with(this).load(R.drawable.loading_gif).apply(options).into(
            view.loading.imgLoading
        )
    }

    private fun getPortfolio(apiKey:String, userId: String) {
        viewModel.getPortfolio(apiKey, userId)
    }

    private fun setObservers() {
        viewModel.portfolioResult.observe(this) { result ->
            when (result) {
                is PortfolioResult.Success -> {
                    showPortfolio(result.portfolio)
                }
                else -> {
                    showMessage(this.getString(R.string.portfolio_error_get_portfolio))
                    goToLogin()
                }
            }
        }

        viewModel.analysisResult.observe(this) { result ->
            when (result) {
                is AnalysisResult.Success -> {

                    showPortfolioAnalysis(result.analysis)
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
        with(view) {
            portfolio.visibility = View.VISIBLE
            portfolioLoading.visibility = View.GONE
        }
    }

    private fun showPortfolioAnalysis(analysis: AnalysisEntity) {
        with(view) {
            txtBitcoinPrice.text = analysis.bitcoinPriceInBrl.parseToCurrency()
            txtTotalAmountValue.text = analysis.amountValue.parseToCurrency()
            txtProfits.text = analysis.profits.parseToPercentage()
        }
    }

    private fun getAnalysis(apiKey: String, userId: String) {
        viewModel.getAnalysis(apiKey, userId)
    }

    private fun showPortfolio(portfolio: PortfolioEntity) {
        with(view) {
            txtAmount.text = portfolio.amount.parseSatoshiToBitcoin()
            txtAveragePrice.text = portfolio.averagePrice.parseToCurrency()
            txtPaidValue.text = portfolio.totalPaidValue.parseToCurrency()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setListeners(userId: String) {
        with(view) {
            menu.btnAddAmount.setOnClickListener {
                goToAddAmount(userId)
            }
            menu.btnRemoveAmount.setOnClickListener {
                goToRemoveAmount(userId)
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

    private fun goToRemoveAmount(userId: String) {
        removeAmountModal = RemoveAmountFragment().apply {
            arguments = Bundle().apply {
                putString(RemoveAmountFragment.KEY_USER_ID, userId)
            }
        }
        removeAmountModal?.show(supportFragmentManager, RemoveAmountFragment.TAG_REMOVE_FUNDS)
    }

    private fun goToAddAmount(userId: String) {
        addAmountModal = AddAmountFragment().apply {
            arguments = Bundle().apply {
                putString(AddAmountFragment.KEY_USER_ID, userId)
            }
        }
        addAmountModal?.show(supportFragmentManager, AddAmountFragment.TAG_ADD_FUNDS)
    }
}