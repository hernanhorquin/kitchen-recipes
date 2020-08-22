package com.example.kitchen_recipes.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val photo: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strInstructions")
    val instructions: String
)

data class Meal(
    @SerializedName("meals")
    val meals: List<Recipe>
)