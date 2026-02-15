package com.example.productdatadesignpattern.ui.screens.favorites.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavProductFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavProductViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
