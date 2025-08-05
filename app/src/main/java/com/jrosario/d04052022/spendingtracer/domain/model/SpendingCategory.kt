package com.jrosario.d04052022.spendingtracer.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jrosario.d04052022.spendingtracer.R

enum class SpendingCategory(@param:StringRes val label: Int, @param:DrawableRes val icon: Int, @param:ColorRes val color: Int) {
    Miscellaneous(R.string.miscellaneous, R.drawable.ic_food_allowance, R.color.category_miscellaneous),
    Food(R.string.food, R.drawable.ic_food_allowance, R.color.category_food),
    Housing(R.string.housing, R.drawable.ic_food_allowance, R.color.category_housing),
    Utilities(R.string.utilities, R.drawable.ic_food_allowance, R.color.category_utilities),
    Entertainment(R.string.entertainment, R.drawable.ic_food_allowance, R.color.category_entertainment),
    Travel(R.string.travel, R.drawable.ic_food_allowance, R.color.category_travel),
    Pets(R.string.pets, R.drawable.ic_food_allowance, R.color.category_pets),
    Education(R.string.education, R.drawable.ic_food_allowance, R.color.category_education),
    Healthcare(R.string.healthcare, R.drawable.ic_food_allowance, R.color.category_healthcare),
}