package com.example.kitchen_recipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchen_recipes.data.model.Recipe
import com.example.kitchen_recipes.data.repository.RecipesRepository
import com.example.kitchen_recipes.ui.utils.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.kitchen_recipes.ui.utils.Result
import com.example.kitchen_recipes.ui.utils.Status

class KitchenRecipeViewModel(private val repository: RecipesRepository): ViewModel() {

    private var _recipes = MutableLiveData<Data<List<Recipe>>>()
    val recipes: LiveData<Data<List<Recipe>>>
        get() = _recipes

    fun getRecipes(recipeName: String) = viewModelScope.launch {
        _recipes.postValue(Data(responseType = Status.LOADING))
        when (val result = withContext(Dispatchers.IO) { repository.getRecipes(recipeName) }) {
            is Result.Failure -> {
                _recipes.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _recipes.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}