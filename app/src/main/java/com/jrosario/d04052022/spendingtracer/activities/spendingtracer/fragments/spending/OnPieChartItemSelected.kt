package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.spending

import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

interface OnPieChartItemSelected : OnChartValueSelectedListener {
    fun onValueSelected(e: PieEntry, h: Highlight?)
}