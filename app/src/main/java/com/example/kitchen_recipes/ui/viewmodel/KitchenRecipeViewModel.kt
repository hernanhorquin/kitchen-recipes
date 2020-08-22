package com.example.kitchen_recipes.ui.viewmodel

import android.os.Handler
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

    private var _randomBanner = MutableLiveData<Data<String>>()
    val randomBanner: LiveData<Data<String>>
        get() = _randomBanner

    private var _delayFinish = MutableLiveData<Boolean>()
    val delayFinish: LiveData<Boolean>
        get() = _delayFinish

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

    fun getRandomBanner() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { repository.getRandomBanner() }) {
            is Result.Failure -> {
                _randomBanner.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _randomBanner.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
                startDelay()
            }
        }
    }

    private fun startDelay() {
        Handler().postDelayed({
            _delayFinish.postValue(true)
        }, 30000)
    }

    fun resetDelay() {
        _delayFinish.postValue(false)
    }
}