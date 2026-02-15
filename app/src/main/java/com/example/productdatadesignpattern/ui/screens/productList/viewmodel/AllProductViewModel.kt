package com.example.productdatadesignpattern.ui.screens.productList.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.productdatadesignpattern.data.repository.DataRepo
import com.example.productdatadesignpattern.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataRepo(application)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()


    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.getAllProducts()
                if (result != null && result.isNotEmpty()) {
                    _products.value = result
                } else {
                    _errorMessage.value = "No products found"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addProductToFav(product: Product) {
        viewModelScope.launch {
            repository.insertProductToFav(product)
        }
    }
    
    fun removeProductFromFav(product: Product) {
        viewModelScope.launch {
            repository.deleteProductFromFav(product)
        }
    }
}
