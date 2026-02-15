package com.example.productdatadesignpattern.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.productdatadesignpattern.model.Product

class LocalData(context: Context) {
    private val productDAO: ProductDAO = AppDataBase.getInstance(context).productDAO()

    suspend fun insertProduct(product: Product) {
        productDAO.insertProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDAO.deleteProduct(product)
    }

    fun getProducts(): LiveData<List<Product>> {
        return productDAO.getProduct()
    }
}
