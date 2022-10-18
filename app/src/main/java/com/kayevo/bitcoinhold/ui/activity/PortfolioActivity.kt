package com.kayevo.bitcoinhold.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.ActivityPortfolioBinding
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.ui.fragment.AddFundsFragment
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PortfolioActivity : AppCompatActivity() {
    private lateinit var portfolioView: ActivityPortfolioBinding
    private val portfolioViewModel by viewModel<PortfolioViewModel>()
    private var addFundsModal: AddFundsFragment? = null

    companion object {
        const val KEY_USER_ID = "USER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portfolioView = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(portfolioView.root)

        setObservers()

        intent.getStringExtra(KEY_USER_ID)?.let { userId ->
            setListeners(userId)
            getPortfolio(userId)
        }
    }

    private fun getPortfolio(userId: String) {
        portfolioViewModel.getPortfolio(userId)
    }

    private fun setObservers() {
        portfolioViewModel.portfolioRepoResult.observe(this) { result ->
            when (result) {
                is PortfolioResult.Success -> {
                    showPortfolio(result.portfolio)
                }
                else -> {
                    showMessage(this.getString(R.string.portfolio_error_get_portfolio))
                }
            }
        }
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
            menu.btnAddBitcoin.setOnClickListener {
                goToAddFunds(userId)
            }
        }
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