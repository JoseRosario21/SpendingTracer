package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.addexpense

import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.utils.DecimalDigitsInputFilter
import com.jrosario.d04052022.spendingtracer.databinding.FragmentAddExpenseBinding

class AddExpenseFragment : Fragment() {
    private lateinit var binding: FragmentAddExpenseBinding
    private lateinit var viewModel: AddExpenseViewModel
    private var selectedPaymentMethod: Int = 0
    private var selectedCategory: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddExpenseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[AddExpenseViewModel::class.java]

        binding.etExpenseAmount.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(5, 2))

        binding.btnAddExpense.setOnClickListener {
            if (binding.etName.text.toString().isBlank() || binding.etExpenseAmount.text.toString().isBlank()) {
                Toast.makeText(requireContext(), getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addExpense(binding.etName.text.toString(), binding.etExpenseAmount.text.toString().toDouble(), selectedCategory, selectedPaymentMethod)
                Toast.makeText(requireContext(), getString(R.string.expense_added_successfully), Toast.LENGTH_SHORT).show()
                binding.etName.setText("")
                binding.etExpenseAmount.setText("")
            }
        }

        val paymentMethods = arrayListOf(getString(R.string.bank_balance), getString(R.string.cash), getString(R.string.food_allowance), getString(R.string.saving))
        val paymentMethodAdapter = ArrayAdapter(requireContext(), R.layout.item_spiner, paymentMethods)
        binding.spPaymentMethod.adapter = paymentMethodAdapter
        binding.spPaymentMethod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedPaymentMethod = paymentMethods.indexOf(element = binding.spPaymentMethod.selectedItem)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val expensesCategories = arrayListOf(
            getString(R.string.entertainment),
            getString(R.string.food),
            getString(R.string.healthcare),
            getString(R.string.housing),
            getString(R.string.miscellaneous),
            getString(R.string.transportation),
            getString(R.string.utilities)
        )
        val categoriesAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, expensesCategories)
        binding.spExpenseCategory.adapter = categoriesAdapter
        binding.spExpenseCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCategory = expensesCategories.indexOf(element = binding.spExpenseCategory.selectedItem)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    companion object {
        fun newInstance() = AddExpenseFragment()
    }
}