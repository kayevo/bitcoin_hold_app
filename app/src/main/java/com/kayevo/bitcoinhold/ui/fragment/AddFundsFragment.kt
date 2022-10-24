package com.kayevo.bitcoinhold.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentAddFundsBinding
import com.kayevo.bitcoinhold.ui.result.AddFundsResult
import com.kayevo.bitcoinhold.ui.viewmodel.AddFundsViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFundsFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _addFundView: FragmentAddFundsBinding? = null
    private val addFundView get() = _addFundView!!
    private val viewModel by viewModel<AddFundsViewModel>()
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
        _addFundView = FragmentAddFundsBinding.inflate(inflater, container, false)
        setListeners()
        setObservers()
        return addFundView.root
    }

    private fun setObservers() {
        viewModel.addFundsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AddFundsResult.Success -> {
                    updatePortfolio()
                    goToPortfolio()
                }
                else -> {
                    showMessage(this.getString(R.string.add_funds_error_to_add))
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
            btnAddFunds.setOnClickListener {
                val bitcoinAmount = txtBitcoinAmount.text.toString()
                val price = txtPrice.text.toString()
                if (viewModel.isValidForm(bitcoinAmount, price)) {
                    addFunds()
                }else{
                    showMessage(this@AddFundsFragment.getString(R.string.add_funds_invalid_form))
                }
            }
        }
    }

    private fun addFunds() {
        with(addFundView) {
            userId?.let { userId ->
                viewModel.removeFunds(
                    userId,
                    txtBitcoinAmount.text.toString(),
                    txtPrice.text.toString()
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
        const val TAG_ADD_FUNDS = "ADD_FUNDS"
    }
}