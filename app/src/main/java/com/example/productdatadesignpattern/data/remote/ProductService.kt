package com.example.productdatadesignpattern.data.remote

import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProduct(): ProductRepons
}
