package com.example.productdatadesignpattern.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.productdatadesignpattern.data.local.LocalData
import com.example.productdatadesignpattern.data.remote.Remote
import com.example.productdatadesignpattern.model.Product

class DataRepo(context: Context) {
    private val localData = LocalData(context)
    private val remote = Remote()

    suspend fun getAllProducts(): List<Product>? {
        return remote.getProducts()
    }

    fun getFavProducts(): LiveData<List<Product>> {
        return localData.getProducts()
    }

    suspend fun insertProductToFav(product: Product) {
        localData.insertProduct(product)
    }

    suspend fun deleteProductFromFav(product: Product) {
        localData.deleteProduct(product)
    }
}
