package com.jrosario.d04052022.spendingtracer.activities.spendingcategorydetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.models.SpendingLog

class SpendingCategoryDetailsAdapter(private val mList: List<SpendingLog>) : RecyclerView.Adapter<SpendingCategoryDetailsAdapter.SpendingLogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense_details, parent, false)

        return SpendingLogViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: SpendingLogViewHolder, position: Int) {
        val item = mList[position]
        holder.expenseName.text = item.name
        holder.expenseDate.text = item.date
        holder.expenseAmount.text = String.format("$%s", item.amount.toString())
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class SpendingLogViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val expenseName: TextView = itemView.findViewById(R.id.tvExpenseName)
        val expenseDate: TextView = itemView.findViewById(R.id.tvExpenseDate)
        val expenseAmount: TextView = itemView.findViewById(R.id.tvExpenseAmount)
    }
}