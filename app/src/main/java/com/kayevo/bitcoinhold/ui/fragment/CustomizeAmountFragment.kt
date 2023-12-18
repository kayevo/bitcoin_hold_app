package com.kayevo.bitcoinhold.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.R
import com.kayevo.bitcoinhold.databinding.FragmentCustomizeAmountBinding
import com.kayevo.bitcoinhold.ui.activity.PortfolioActivity
import com.kayevo.bitcoinhold.ui.result.CustomizeAmountResult
import com.kayevo.bitcoinhold.ui.viewmodel.CustomizeAmountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomizeAmountFragment : BottomSheetDialogFragment() {
    private var userId: String? = null
    private var _customizeAmountView: FragmentCustomizeAmountBinding? = null
    private val customizeAmountView get() = _customizeAmountView!!
    private val viewModel by viewModel<CustomizeAmountViewModel>()

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
        _customizeAmountView = FragmentCustomizeAmountBinding.inflate(
            inflater, container, false
        )
        setListeners()
        setObservers()
        return customizeAmountView.root
    }

    private fun setListeners() {
        with(customizeAmountView) {
            btnCustomizeAmount.setOnClickListener {
                val amount = txtAmount.text.toString()
                val paidValue = txtPaidValue.text.toString()
                if (viewModel.isValidForm(amount, paidValue)) {
                    customizeAmount(BuildConfig.BITCOIN_HOLD_API_KEY)
                } else {
                    showMessage(
                        this@CustomizeAmountFragment.getString(R.string.customize_amount_invalid_form)
                    )
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.customizeAmountResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is CustomizeAmountResult.Success -> {
                    goToPortfolio()
                }
                else -> {
                    showMessage(this.getString(R.string.customize_amount_error_to_customize))
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

    private fun customizeAmount(apiKey: String) {
        with(customizeAmountView) {
            userId?.let { userId ->
                viewModel.customizeAmount(
                    apiKey = apiKey,
                    userId,
                    txtAmount.text.toString(),
                    txtPaidValue.text.toString()
                )
            }
        }
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_CUSTOMIZE_FUNDS = "CUSTOMIZE_FUNDS"
    }
}