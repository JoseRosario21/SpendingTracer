package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.home

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.utils.DecimalDigitsInputFilter
import com.jrosario.d04052022.spendingtracer.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var selectedMoneyCategory: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        viewModel.accountLog.observe(viewLifecycleOwner) { accountLogFromDB ->
            val sum = viewModel.sum(accountLogFromDB)

            binding.tvAccountBalance.text = String.format(Locale.getDefault(), getString(R.string.format_currency), sum)
            if (sum < 0f)
                binding.rlBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_red_round_corners)
            else {
                binding.rlBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_round_corners)
            }

            binding.tvBankBalanceAmount.text = String.format(Locale.getDefault(), getString(R.string.format_currency), accountLogFromDB.bankAmount)
            if (accountLogFromDB.bankAmount < 0f)
                binding.llBankBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_red_round_corners)
            else {
                binding.llBankBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_round_corners)
            }

            binding.tvCashAmount.text = String.format(Locale.getDefault(), getString(R.string.format_currency), accountLogFromDB.cashAmount)
            if (accountLogFromDB.cashAmount < 0f)
                binding.llCashBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_red_round_corners)
            else {
                binding.llCashBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_round_corners)
            }

            binding.tvFoodAllowanceAmount.text = String.format(Locale.getDefault(), getString(R.string.format_currency), accountLogFromDB.foodAllowanceAmount)
            if (accountLogFromDB.foodAllowanceAmount < 0f)
                binding.llFoodAllowanceBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_red_round_corners)
            else {
                binding.llFoodAllowanceBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_round_corners)
            }

            binding.tvSavingAmount.text = String.format(Locale.getDefault(), getString(R.string.format_currency), accountLogFromDB.savingAmount)
            if (accountLogFromDB.savingAmount < 0f)
                binding.llSavingBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_red_round_corners)
            else {
                binding.llSavingBalance.background = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_background_round_corners)
            }
        }

        binding.etAddMoneyAmount.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(5, 2))

        binding.btnAddMoney.setOnClickListener {
            if (binding.etAddMoneyAmount.text.toString().isBlank()) {
                Toast.makeText(requireContext(), getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addMoneyToAccount(binding.etAddMoneyAmount.text.toString().toDouble(), selectedMoneyCategory)
                binding.etAddMoneyAmount.setText("")
            }
        }

        val arrayOfOptions = arrayListOf(getString(R.string.bank_balance), getString(R.string.cash), getString(R.string.food_allowance), getString(R.string.saving))
        val adapter = ArrayAdapter(requireContext(), R.layout.item_spiner, arrayOfOptions)
        binding.spMoneyCategory.adapter = adapter
        binding.spMoneyCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedMoneyCategory = arrayOfOptions.indexOf(element = binding.spMoneyCategory.selectedItem)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        viewModel.getAccountLogFromDB()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}