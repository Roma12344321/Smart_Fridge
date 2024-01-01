package com.dev.smart_fridge.di

import android.app.Application
import com.dev.smart_fridge.data.ProductDao
import com.dev.smart_fridge.data.ProductDataBase
import com.dev.smart_fridge.data.ProductRepositoryImpl
import com.dev.smart_fridge.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    companion object {
        @Provides
        fun provideProductDao(application: Application): ProductDao {
            return ProductDataBase.getInstance(application).productDao()
        }
    }
}