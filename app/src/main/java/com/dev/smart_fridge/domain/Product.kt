package com.dev.smart_fridge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val item: String,
    val time: Int
)