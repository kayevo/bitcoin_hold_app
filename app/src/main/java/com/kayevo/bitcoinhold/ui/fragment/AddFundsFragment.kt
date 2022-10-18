package com.kayevo.bitcoinhold.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kayevo.bitcoinhold.databinding.FragmentAddFundsBinding
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
        return addFundView.root
    }

    companion object {
        const val KEY_USER_ID = "USER_ID"
        const val TAG_ADD_FUNDS = "ADD_FUNDS"
    }
}