package com.kayevo.bitcoinhold.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentRemoveFundsBinding
import com.kayevo.bitcoinhold.ui.result.AddFundsResult
import com.kayevo.bitcoinhold.ui.result.RemoveFundsResult
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.RemoveFundsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveFundsFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _removeFundsView: FragmentRemoveFundsBinding? = null
    private val removeFundsView get() = _removeFundsView!!
    private val viewModel by viewModel<RemoveFundsViewModel>()
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
        _removeFundsView = FragmentRemoveFundsBinding.inflate(
            inflater, container, false
        )
        setListeners()
        setObservers()
        return removeFundsView.root
    }

    private fun setObservers() {
        viewModel.removeFundsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RemoveFundsResult.Success -> {
                    updatePortfolio()
                    goToPortfolio()
                }
                is RemoveFundsResult.InsufficientFunds -> {
                    showMessage(this.getString(R.string.remove_funds_insufficient_funds))
                }
                else -> {
                    showMessage(this.getString(R.string.remove_funds_error_to_remove))
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
        with(removeFundsView) {
            btnRemoveFunds.setOnClickListener {
                val bitcoinAmount = txtBitcoinAmount.text.toString()
                if (viewModel.isValidForm(bitcoinAmount)) {
                    removeFunds()
                }else{
                    showMessage(
                        this@RemoveFundsFragment.getString(R.string.remove_funds_invalid_form)
                    )
                }
            }
        }
    }

    private fun removeFunds() {
        with(removeFundsView) {
            userId?.let { userId ->
                viewModel.removeFunds(
                    userId,
                    txtBitcoinAmount.text.toString()
                )
            }
        }
    }

    private fun updatePortfolio() {
        userId?.let { userId ->
            portfolioViewModel.getPortfolio(userId)
        }
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_REMOVE_FUNDS = "REMOVE_FUNDS"
    }
}