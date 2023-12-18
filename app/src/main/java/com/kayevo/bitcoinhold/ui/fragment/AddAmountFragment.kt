package com.kayevo.bitcoinhold.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentAddAmountBinding
import com.kayevo.bitcoinhold.ui.result.AddAmountResult
import com.kayevo.bitcoinhold.ui.viewmodel.AddAmountViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddAmountFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _addFundView: FragmentAddAmountBinding? = null
    private val addFundView get() = _addFundView!!
    private val viewModel by viewModel<AddAmountViewModel>()
    private val portfolioViewModel by sharedViewModel<PortfolioViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(KEY_USER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _addFundView = FragmentAddAmountBinding.inflate(inflater, container, false)
        setListeners()
        setObservers()
        return addFundView.root
    }

    private fun setObservers() {
        viewModel.addAmountResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AddAmountResult.Success -> {
                    updatePortfolio(BuildConfig.BITCOIN_HOLD_API_KEY)
                    goToPortfolio()
                }
                else -> {
                    showMessage(this.getString(R.string.add_amount_error_to_add))
                }
            }
        }
    }

    private fun goToPortfolio() {
        dismiss()
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setListeners() {
        with(addFundView) {
            btnAddAmount.setOnClickListener {
                val amount = txtAmount.text.toString()
                val paidValue = txtPaidValue.text.toString()
                if (viewModel.isValidForm(amount, paidValue)) {
                    addAmount(BuildConfig.BITCOIN_HOLD_API_KEY)
                }else{
                    showMessage(this@AddAmountFragment.getString(R.string.add_amount_invalid_form))
                }
            }
        }
    }

    private fun addAmount(apiKey: String) {
        with(addFundView) {
            userId?.let { userId ->
                viewModel.addAmount(
                    apiKey,
                    userId,
                    txtAmount.text.toString(),
                    txtPaidValue.text.toString()
                )
            }
        }
    }

    private fun updatePortfolio(apiKey: String) {
        userId?.let { userId ->
            portfolioViewModel.getPortfolio(apiKey, userId)
            portfolioViewModel.getAnalysis(apiKey, userId)
        }
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_ADD_FUNDS = "ADD_FUNDS"
    }
}