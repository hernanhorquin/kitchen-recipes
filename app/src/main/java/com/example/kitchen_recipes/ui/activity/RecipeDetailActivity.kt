package com.example.kitchen_recipes.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.adapter.IngredientAdapter
import com.example.kitchen_recipes.ui.utils.Constants.RECIPE_ID
import com.example.kitchen_recipes.ui.utils.Status
import com.example.kitchen_recipes.ui.viewmodel.RecipeDetailViewModel
import kotlinx.android.synthetic.main.activity_recipe_detail.progressBar
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_image
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_ingredients_title
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_instruction
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_name
import kotlinx.android.synthetic.main.activity_recipe_detail.recycler_ingredients

class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel = RecipeDetailViewModel(RecipesRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        intent.getStringExtra(RECIPE_ID)?.let {
            viewModel.getRecipes(it)
        }

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.recipe.observe(this, Observer {
            when (it.responseType) {
                Status.ERROR -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { recipeDetails ->
                        recipe_name.text = recipeDetails.name
                        recipe_instruction.text = recipeDetails.instructions
                        Glide.with(this).load(recipeDetails.photo).into(recipe_image)
                        val ingredients = listOf(recipeDetails.ingredient1, recipeDetails.ingredient2, recipeDetails.ingredient3, recipeDetails.ingredient4, recipeDetails.ingredient5, recipeDetails.ingredient6, recipeDetails.ingredient7, recipeDetails.ingredient8, recipeDetails.ingredient9, recipeDetails.ingredient10, recipeDetails.ingredient11, recipeDetails.ingredient12, recipeDetails.ingredient13, recipeDetails.ingredient14, recipeDetails.ingredient15, recipeDetails.ingredient16, recipeDetails.ingredient17, recipeDetails.ingredient18, recipeDetails.ingredient19, recipeDetails.ingredient20)
                        recycler_ingredients.layoutManager = LinearLayoutManager(this)
                        recycler_ingredients.adapter = IngredientAdapter(ingredients.filter { ingredient -> ingredient.isNotEmpty() })
                        hideLoading()
                    }
                }
            }
        })
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recipe_ingredients_title.visibility = View.VISIBLE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recipe_ingredients_title.visibility = View.GONE
    }
}