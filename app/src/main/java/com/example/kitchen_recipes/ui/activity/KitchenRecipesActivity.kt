package com.example.kitchen_recipes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kitchen_recipes.R
import com.example.kitchen_recipes.data.model.Recipe
import com.example.kitchen_recipes.ui.adapter.RecipeAdapter
import kotlinx.android.synthetic.main.activity_kitchen_recipes.kitchen_recipes_recycler
import kotlinx.android.synthetic.main.activity_kitchen_recipes.search_view

class KitchenRecipesActivity : AppCompatActivity() {

    private var adapter: RecipeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitchen_recipes)

        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        kitchen_recipes_recycler.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter(
            listOf(
            )
        ) {

        }
        kitchen_recipes_recycler.adapter = adapter
    }
}