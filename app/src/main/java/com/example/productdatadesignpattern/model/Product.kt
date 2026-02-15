package com.example.productdatadesignpattern.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: Long,
    val title: String,
    val price: Double,
    val thumbnail: String,
    @Ignore
    val images: List<String>?
) {
    constructor(id: Long, title: String, price: Double, thumbnail: String)
        : this(id, title, price, thumbnail, null)

    val image: String
        get() = images?.firstOrNull() ?: thumbnail
}
