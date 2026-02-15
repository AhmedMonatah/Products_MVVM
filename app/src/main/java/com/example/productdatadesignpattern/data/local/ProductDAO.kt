package com.example.productdatadesignpattern.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productdatadesignpattern.model.Product

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM product")
    fun getProduct(): LiveData<List<Product>>

    @Delete
    suspend fun deleteProduct(product: Product)
}
