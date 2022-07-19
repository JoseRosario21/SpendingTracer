package com.jrosario.d04052022.spendingtracer.activities.spendingcategorydetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.databinding.ActivitySpendingCategoryDetailsBinding
import com.jrosario.d04052022.spendingtracer.models.SpendingLog

class SpendingCategoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpendingCategoryDetailsBinding
    private lateinit var viewModel: SpendingCategoryDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpendingCategoryDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        val categoryName = intent.extras?.getString("CATEGORY_NAME")

        viewModel = ViewModelProvider(this)[SpendingCategoryDetailsViewModel::class.java]

        viewModel.spendingLogs.observe(this) { spendingLogs ->
            val logsToShow = arrayListOf<SpendingLog>()
            for (log in spendingLogs) {
                when (log.category) {
                    0 -> {
                        if (getString(R.string.entertainment) == categoryName)
                            logsToShow.add(log)
                    }
                    1 -> {
                        if (getString(R.string.food) == categoryName)
                            logsToShow.add(log)
                    }
                    2 -> {
                        if (getString(R.string.healthcare) == categoryName)
                            logsToShow.add(log)
                    }
                    3 -> {
                        if (getString(R.string.housing) == categoryName)
                            logsToShow.add(log)
                    }
                    4 -> {
                        if (getString(R.string.miscellaneous) == categoryName)
                            logsToShow.add(log)
                    }
                    5 -> {
                        if (getString(R.string.transportation) == categoryName)
                            logsToShow.add(log)
                    }
                    6 -> {
                        if (getString(R.string.utilities) == categoryName)
                            logsToShow.add(log)
                    }
                }
            }
            val adapter = SpendingCategoryDetailsAdapter(logsToShow)
            binding.rvSpending.adapter = adapter
        }

        binding.tvCategoryName.text = categoryName
        binding.rvSpending.layoutManager = LinearLayoutManager(this)
        viewModel.getSpendingLogs()
    }
}
