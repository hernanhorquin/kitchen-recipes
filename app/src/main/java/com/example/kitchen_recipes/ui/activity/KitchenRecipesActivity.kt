package com.example.kitchen_recipes.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.adapter.RecipeAdapter
import com.example.kitchen_recipes.ui.utils.Status
import com.example.kitchen_recipes.ui.viewmodel.KitchenRecipeViewModel
import kotlinx.android.synthetic.main.activity_kitchen_recipes.kitchen_recipes_recycler
import kotlinx.android.synthetic.main.activity_kitchen_recipes.progressBar
import kotlinx.android.synthetic.main.activity_kitchen_recipes.search_view

class KitchenRecipesActivity : AppCompatActivity() {

    private val viewModel = KitchenRecipeViewModel(RecipesRepository())
    private var adapter = RecipeAdapter() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitchen_recipes)

        setUpObservers()

        kitchen_recipes_recycler.layoutManager = LinearLayoutManager(this)

    }

    private fun setUpObservers() {
        viewModel.recipes.observe(this, Observer {
            when (it.responseType) {
                Status.ERROR -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESSFUL -> {
                    it.data?.let { recipeList ->
                        adapter.update(recipeList)
                        kitchen_recipes_recycler.adapter = adapter
                        hideLoading()
                    }
                }
            }
        })

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getRecipes(newText ?: "")
                return false
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