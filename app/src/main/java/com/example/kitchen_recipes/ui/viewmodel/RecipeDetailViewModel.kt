package com.example.kitchen_recipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchen_recipes.data.model.Recipe
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.utils.Data
import com.example.kitchen_recipes.ui.utils.Result
import com.example.kitchen_recipes.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailViewModel(private val repository: RecipesRepository): ViewModel() {

    private var _recipe = MutableLiveData<Data<Recipe>>()
    val recipe: LiveData<Data<Recipe>>
        get() = _recipe

    fun getRecipes(recipeId: String) = viewModelScope.launch {
        _recipe.postValue(Data(responseType = Status.LOADING))
        when (val result = withContext(Dispatchers.IO) { repository.getInstructions(recipeId) }) {
            is Result.Failure -> {
                _recipe.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _recipe.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}