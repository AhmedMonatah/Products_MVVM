package com.example.productdatadesignpattern.ui.screens.productList.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AllProductFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllProductViewModel::class.java)) {
            return AllProductViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
