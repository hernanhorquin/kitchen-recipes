package com.example.kitchen_recipes.data.api

import com.example.kitchen_recipes.data.model.Meal
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiService {

    @GET("search.php")
    fun getRecipes(@Query("s") recipeName: String): Call<Meal>

    @GET("lookup.php")
    fun getInstructions(@Query("i") recipeId: String): Call<Meal>
}