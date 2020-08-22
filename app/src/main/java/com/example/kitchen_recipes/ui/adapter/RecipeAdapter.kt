package com.example.kitchen_recipes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.recipe_name
import kotlinx.android.synthetic.main.item_recipe.view.recipe_category
import kotlinx.android.synthetic.main.item_recipe.view.recipe_image

class RecipeAdapter(val getId: (String) -> Unit) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    var recipeList = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe,
                parent,
                false
            ), getId
        )

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun update(list: List<Recipe>) {
        recipeList.clear()
        recipeList.addAll(list)
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(view: View, val getId: (String) -> Unit) : RecyclerView.ViewHolder(view) {
    fun bind(item: Recipe) = with(itemView) {
        setOnClickListener {
            getId(item.id)
        }
        recipe_name.text = item.name
        recipe_category.text = item.category
        Glide.with(this).load(item.photo).into(recipe_image)
    }
}