package com.jrosario.d04052022.spendingtracer.activities.spendingtracer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.addexpense.AddExpenseFragment
import com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.home.HomeFragment
import com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.spending.SpendingFragment
import com.jrosario.d04052022.spendingtracer.databinding.ActivitySpendingTracerBinding

class SpendingTracerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpendingTracerBinding
    private lateinit var fragments: ArrayList<Fragment>
    private var currentFragment = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpendingTracerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        fragments = arrayListOf(
            HomeFragment.newInstance(),
            AddExpenseFragment.newInstance(),
            SpendingFragment.newInstance(),
        )

        changeFragment(binding.bottomNavigationView.selectedItemId)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    setCurrentFragment(fragments[0])
                    currentFragment = 0
                }
                R.id.add_expense -> {
                    setCurrentFragment(fragments[1])
                    currentFragment = 1
                }
                R.id.spending -> {
                    setCurrentFragment(fragments[2])
                    currentFragment = 2
                }
            }
            true
        }
    }

    private fun changeFragment(id: Int) {
        when (id) {
            0 or R.id.home -> {
                setCurrentFragment(fragments[0])
                currentFragment = 0
            }
            R.id.add_expense -> {
                setCurrentFragment(fragments[1])
                currentFragment = 1
            }
            R.id.spending -> {
                setCurrentFragment(fragments[2])
                currentFragment = 2
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}