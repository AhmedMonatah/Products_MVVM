package com.example.productdatadesignpattern.ui.screens.favorites.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.productdatadesignpattern.data.repository.DataRepo
import com.example.productdatadesignpattern.model.Product
import kotlinx.coroutines.launch

class FavProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataRepo(application)

    val favoriteProducts: LiveData<List<Product>> = repository.getFavProducts()

    fun removeProductFromFav(product: Product) {
        viewModelScope.launch {
            repository.deleteProductFromFav(product)
        }
    }
}
