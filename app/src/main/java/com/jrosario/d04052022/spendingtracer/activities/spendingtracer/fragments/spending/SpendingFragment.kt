package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.spending

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.activities.spendingcategorydetails.SpendingCategoryDetailsActivity
import com.jrosario.d04052022.spendingtracer.databinding.FragmentSpendingBinding
import com.jrosario.d04052022.spendingtracer.models.SpendingLog

class SpendingFragment : Fragment(), OnPieChartItemSelected {
    private lateinit var binding: FragmentSpendingBinding
    private lateinit var viewModel: SpendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpendingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SpendingViewModel::class.java]

        viewModel.spendingLogs.observe(viewLifecycleOwner) { spendingLogs ->
            var entertainmentSpentAmount = 0.0
            var foodSpentAmount = 0.0
            var healthcareSpentAmount = 0.0
            var housingSpentAmount = 0.0
            var miscellaneousSpentAmount = 0.0
            var transportationSpentAmount = 0.0
            var utilitiesSpentAmount = 0.0

            for (entry: SpendingLog in spendingLogs) {
                when (entry.category) {
                    0 -> entertainmentSpentAmount += entry.amount
                    1 -> foodSpentAmount += entry.amount
                    2 -> healthcareSpentAmount += entry.amount
                    3 -> housingSpentAmount += entry.amount
                    4 -> miscellaneousSpentAmount += entry.amount
                    5 -> transportationSpentAmount += entry.amount
                    6 -> utilitiesSpentAmount += entry.amount
                }
            }
            val dataEntries = ArrayList<PieEntry>()
            val colors: ArrayList<Int> = ArrayList()

            if (entertainmentSpentAmount != 0.0) {
                dataEntries.add(PieEntry(entertainmentSpentAmount.toFloat(), getString(R.string.entertainment)))
                colors.add(Color.parseColor("#4DD0E1"))
            }

            if (foodSpentAmount != 0.0) {
                dataEntries.add(PieEntry(foodSpentAmount.toFloat(), getString(R.string.food)))
                colors.add(Color.parseColor("#FFF176"))
            }

            if (healthcareSpentAmount != 0.0) {
                dataEntries.add(PieEntry(healthcareSpentAmount.toFloat(), getString(R.string.healthcare)))
                colors.add(Color.parseColor("#FAA08C"))
            }

            if (housingSpentAmount != 0.0) {
                dataEntries.add(PieEntry(housingSpentAmount.toFloat(), getString(R.string.housing)))
                colors.add(Color.parseColor("#ECEA69"))
            }

            if (miscellaneousSpentAmount != 0.0) {
                dataEntries.add(PieEntry(miscellaneousSpentAmount.toFloat(), getString(R.string.miscellaneous)))
                colors.add(Color.parseColor("#69ECC1"))
            }

            if (transportationSpentAmount != 0.0) {
                dataEntries.add(PieEntry(transportationSpentAmount.toFloat(), getString(R.string.transportation)))
                colors.add(Color.parseColor("#B769EC"))
            }

            if (utilitiesSpentAmount != 0.0) {
                dataEntries.add(PieEntry(utilitiesSpentAmount.toFloat(), getString(R.string.utilities)))
                colors.add(Color.parseColor("#77EC69"))
            }

            if (dataEntries.isEmpty()) {
                dataEntries.add(PieEntry(0f, getString(R.string.no_expenses)))
                colors.add(Color.parseColor("#A0A0A0"))
                binding.pcSpending.legend.isEnabled = false
            } else {
                binding.pcSpending.legend.isEnabled = true
            }

            val dataSet = PieDataSet(dataEntries, "")
            val data = PieData(dataSet)

            dataSet.sliceSpace = 2f
            dataSet.colors = colors
            binding.pcSpending.data = data
            data.setValueTextSize(15f)
            binding.pcSpending.setOnChartValueSelectedListener(this@SpendingFragment)
            binding.pcSpending.centerText = String.format(getString(R.string.total_spent), data.yValueSum)
        }

        with(binding.pcSpending) {
            description.text = ""
            setTouchEnabled(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.textSize = 12f
            legend.isWordWrapEnabled = true
            when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    legend.textColor = Color.WHITE
                    setHoleColor(Color.BLACK)
                    setCenterTextColor(Color.WHITE)
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    legend.textColor = Color.BLACK
                    setHoleColor(Color.WHITE)
                    setCenterTextColor(Color.BLACK)
                }
            }

            holeRadius = 50f
            transparentCircleRadius = 55f
            isDrawHoleEnabled = true
            setExtraOffsets(5f, 5f, 5f, 20f)
            animateY(1400, Easing.EaseInOutQuad)
            setCenterTextSize(20f)
        }

        viewModel.getSpendingLogs()
    }

    companion object {
        fun newInstance() = SpendingFragment()
    }

    override fun onValueSelected(e: PieEntry, h: Highlight?) {
        if (h != null) {
            val intent = Intent(requireContext(), SpendingCategoryDetailsActivity::class.java)
            intent.putExtra("CATEGORY_NAME", e.label)
            startActivity(intent)
        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        onValueSelected(e as PieEntry, h)
    }

    override fun onNothingSelected() {
    }
}
