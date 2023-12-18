package com.kayevo.bitcoinhold.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentRemoveAmountBinding
import com.kayevo.bitcoinhold.ui.result.RemoveAmountResult
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.RemoveAmountViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveAmountFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _removeAmountView: FragmentRemoveAmountBinding? = null
    private val removeAmountView get() = _removeAmountView!!
    private val viewModel by viewModel<RemoveAmountViewModel>()
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
        _removeAmountView = FragmentRemoveAmountBinding.inflate(
            inflater, container, false
        )
        setListeners()
        setObservers()
        return removeAmountView.root
    }

    private fun setObservers() {
        viewModel.removeAmountResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RemoveAmountResult.Success -> {
                    updatePortfolio(BuildConfig.BITCOIN_HOLD_API_KEY)
                    goToPortfolio()
                }
                is RemoveAmountResult.InsufficientAmount -> {
                    showMessage(this.getString(R.string.remove_amount_insufficient_amount))
                }
                else -> {
                    showMessage(this.getString(R.string.remove_amount_error_to_remove))
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
        with(removeAmountView) {
            btnRemoveAmount.setOnClickListener {
                val bitcoinAmount = txtAmount.text.toString()
                if (viewModel.isValidForm(bitcoinAmount)) {
                    removeAmount(BuildConfig.BITCOIN_HOLD_API_KEY)
                }else{
                    showMessage(
                        this@RemoveAmountFragment.getString(R.string.remove_amount_invalid_form)
                    )
                }
            }
        }
    }

    private fun removeAmount(apiKey: String) {
        with(removeAmountView) {
            userId?.let { userId ->
                viewModel.removeAmount(
                    apiKey = apiKey,
                    userId,
                    txtAmount.text.toString(),
                )
            }
        }
    }

    private fun updatePortfolio(apiKey: String) {
        userId?.let { userId ->
            portfolioViewModel.getPortfolio(apiKey, userId)
        }
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_REMOVE_FUNDS = "REMOVE_FUNDS"
    }
}