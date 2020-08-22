package com.example.kitchen_recipes.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.adapter.RecipeAdapter
import com.example.kitchen_recipes.ui.utils.Constants.RECIPE_ID
import com.example.kitchen_recipes.ui.utils.Status
import com.example.kitchen_recipes.ui.viewmodel.KitchenRecipeViewModel
import kotlinx.android.synthetic.main.activity_kitchen_recipes.banner
import kotlinx.android.synthetic.main.activity_kitchen_recipes.kitchen_recipes_recycler
import kotlinx.android.synthetic.main.activity_kitchen_recipes.progressBar
import kotlinx.android.synthetic.main.activity_kitchen_recipes.search_view

class KitchenRecipesActivity : AppCompatActivity() {

    private val viewModel = KitchenRecipeViewModel(RecipesRepository())
    private var adapter = RecipeAdapter() {
        val intent = Intent(applicationContext, RecipeDetailActivity::class.java)
        intent.putExtra(RECIPE_ID, it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitchen_recipes)

        setUpObservers()
        viewModel.getRandomBanner()
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

        viewModel.randomBanner.observe(this, Observer {
            when (it.responseType) {
                Status.SUCCESSFUL -> {
                    it.data?.let { bannerPhoto ->
                        Glide.with(this).load(bannerPhoto).into(banner)
                    }
                }
                else -> {
                    Toast.makeText(this, getString(R.string.load_banner_error_msg), Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.delayFinish.observe(this, Observer {
            if (it) {
                viewModel.getRandomBanner()
                viewModel.resetDelay()
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