package com.example.productdatadesignpattern.data.remote

import com.example.productdatadesignpattern.model.Product
import com.google.gson.annotations.SerializedName

data class ProductRepons(
    @SerializedName("products")
    val results: List<Product>
)
