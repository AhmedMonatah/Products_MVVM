package com.example.productdatadesignpattern.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.productdatadesignpattern.model.Product

@Database(entities = [Product::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "productsdb"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}
