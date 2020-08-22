package com.example.kitchen_recipes.data.repository

import com.example.kitchen_recipes.data.KitchenRecipeRequestGenerator
import com.example.kitchen_recipes.data.api.ApiService
import com.example.kitchen_recipes.data.model.Recipe
import com.example.kitchen_recipes.ui.utils.Constants.BAD_REQUEST
import com.example.kitchen_recipes.ui.utils.Result

class RecipesRepository {

    private val apiService = KitchenRecipeRequestGenerator()

    fun getRecipes(recipeName: String): Result<List<Recipe>> {
        val callResponse = apiService.createService(ApiService::class.java)
            .getRecipes(recipeName)
        val response = callResponse.execute()
        response?.let { _ ->
            if (response.isSuccessful) {
                response.body()?.let { meals ->
                    meals.meals?.let {
                        return Result.Success(it)
                    }
                    return Result.Success(listOf())
                }
            }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }

    fun getInstructions(recipeId: String): Result<Recipe> {
        val callResponse = apiService.createService(ApiService::class.java)
            .getInstructions(recipeId)
        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful) {
                response.body()?.let { meals ->
                    meals.meals?.let {
                        return Result.Success(it.get(0))
                    }
                }
            }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }

    fun getRandomBanner(): Result<String> {
        val callResponse = apiService.createService(ApiService::class.java)
            .getRandomBanner()
        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful) {
                response.body()?.let { meals ->
                    meals.meals?.let {
                        return Result.Success(it.get(0).photo)
                    }
                }
            }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }
}