package com.example.kitchen_recipes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchen_recipes.R
import kotlinx.android.synthetic.main.item_text_ingredient.view.text_ingredient

class IngredientAdapter(val ingredients: List<String>) :
    RecyclerView.Adapter<IngredientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
        IngredientViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_text_ingredient,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }
}

class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: String) = with(itemView) {
        text_ingredient.text = "- $item"
    }
}