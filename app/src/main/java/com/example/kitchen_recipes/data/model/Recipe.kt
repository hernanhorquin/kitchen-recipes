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
    val instructions: String,
    @SerializedName("strIngredient1")
    val ingredient1: String,
    @SerializedName("strIngredient2")
    val ingredient2: String,
    @SerializedName("strIngredient3")
    val ingredient3: String,
    @SerializedName("strIngredient4")
    val ingredient4: String,
    @SerializedName("strIngredient5")
    val ingredient5: String,
    @SerializedName("strIngredient6")
    val ingredient6: String,
    @SerializedName("strIngredient7")
    val ingredient7: String,
    @SerializedName("strIngredient8")
    val ingredient8: String,
    @SerializedName("strIngredient9")
    val ingredient9: String,
    @SerializedName("strIngredient10")
    val ingredient10: String,
    @SerializedName("strIngredient11")
    val ingredient11: String,
    @SerializedName("strIngredient12")
    val ingredient12: String,
    @SerializedName("strIngredient13")
    val ingredient13: String,
    @SerializedName("strIngredient14")
    val ingredient14: String,
    @SerializedName("strIngredient15")
    val ingredient15: String,
    @SerializedName("strIngredient16")
    val ingredient16: String,
    @SerializedName("strIngredient17")
    val ingredient17: String,
    @SerializedName("strIngredient18")
    val ingredient18: String,
    @SerializedName("strIngredient19")
    val ingredient19: String,
    @SerializedName("strIngredient20")
    val ingredient20: String
)

data class Meal(
    @SerializedName("meals")
    val meals: List<Recipe>
)