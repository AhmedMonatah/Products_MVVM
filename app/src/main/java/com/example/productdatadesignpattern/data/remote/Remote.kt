package com.example.productdatadesignpattern.data.remote

import android.util.Log
import com.example.productdatadesignpattern.model.Product

class Remote {
    private val productService: ProductService = Network.getInstance().productService

    suspend fun getProducts(): List<Product>? {
        Log.d("Remote", "Fetching products from API...")
        val response = productService.getProduct()
        Log.d("Remote", "API Response: ${response.results.size} products")
        return response.results
    }
}
