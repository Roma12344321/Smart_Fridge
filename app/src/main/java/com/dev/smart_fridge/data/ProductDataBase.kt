package com.dev.smart_fridge.data


import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.smart_fridge.domain.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {
    companion object {
        fun getInstance(application: Application): ProductDataBase {
            val instance = Room.databaseBuilder(
                application,
                ProductDataBase::class.java,
                "products.db"
            ).build()
            return instance
        }
    }

    abstract fun productDao(): ProductDao?
}