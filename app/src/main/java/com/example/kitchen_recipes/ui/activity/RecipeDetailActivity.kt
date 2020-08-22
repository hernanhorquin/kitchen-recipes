package com.example.kitchen_recipes.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.utils.Status
import com.example.kitchen_recipes.ui.viewmodel.RecipeDetailViewModel
import kotlinx.android.synthetic.main.activity_recipe_detail.progressBar
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_image
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_instruction
import kotlinx.android.synthetic.main.activity_recipe_detail.recipe_name

class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel = RecipeDetailViewModel(RecipesRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        intent.getStringExtra("recipeId")?.let {
            viewModel.getRecipes(it)
        }

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
                        hideLoading()
                    }
                }
            }
        })
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }
}