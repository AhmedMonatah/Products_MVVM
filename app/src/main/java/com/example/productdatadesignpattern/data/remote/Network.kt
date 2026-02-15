package com.example.productdatadesignpattern.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network private constructor() {
    val productService: ProductService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productService = retrofit.create(ProductService::class.java)
    }

    companion object {
        @Volatile
        private var instance: Network? = null

        fun getInstance(): Network {
            return instance ?: synchronized(this) {
                val newInstance = Network()
                instance = newInstance
                newInstance
            }
        }
    }
}
