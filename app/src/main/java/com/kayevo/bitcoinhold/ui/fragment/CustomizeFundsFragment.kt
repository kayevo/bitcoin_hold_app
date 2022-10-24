package com.kayevo.bitcoinhold.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentCustomizeFundsBinding
import com.kayevo.bitcoinhold.ui.activity.PortfolioActivity
import com.kayevo.bitcoinhold.ui.result.CustomizeFundsResult
import com.kayevo.bitcoinhold.ui.viewmodel.CustomizeFundsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomizeFundsFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _customizeFundsView: FragmentCustomizeFundsBinding? = null
    private val customizeFundsView get() = _customizeFundsView!!
    private val viewModel by viewModel<CustomizeFundsViewModel>()

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
        _customizeFundsView = FragmentCustomizeFundsBinding.inflate(
            inflater, container, false
        )
        setListeners()
        setObservers()
        return customizeFundsView.root
    }

    private fun setListeners() {
        with(customizeFundsView) {
            btnCustomizeFunds.setOnClickListener {
                val bitcoinAmount = txtBitcoinAmount.text.toString()
                val price = txtPrice.text.toString()
                if (viewModel.isValidForm(bitcoinAmount, price)) {
                    customizeFunds()
                } else {
                    showMessage(
                        this@CustomizeFundsFragment.getString(R.string.customize_funds_invalid_form)
                    )
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.customizeFundsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CustomizeFundsResult.Success -> {
                    goToPortfolio()
                }
                else -> {
                    showMessage(this.getString(R.string.customize_funds_error_to_customize))
                }
            }
        }
    }

    private fun goToPortfolio() {
        val intent = Intent(activity, PortfolioActivity::class.java)
        intent.putExtra(PortfolioActivity.KEY_USER_ID, userId)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        dismiss()
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun customizeFunds() {
        with(customizeFundsView) {
            userId?.let { userId ->
                viewModel.customizeFunds(
                    userId,
                    txtBitcoinAmount.text.toString(),
                    txtPrice.text.toString()
                )
            }
        }
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_CUSTOMIZE_FUNDS = "CUSTOMIZE_FUNDS"
    }
}