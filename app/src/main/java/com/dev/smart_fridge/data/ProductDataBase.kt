package com.dev.smart_fridge.data


import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.smart_fridge.domain.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun productDao():ProductDao
    companion object {

        private var INSTANCE: ProductDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "product_item.db"

        fun getInstance(application: Application): ProductDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    ProductDataBase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}