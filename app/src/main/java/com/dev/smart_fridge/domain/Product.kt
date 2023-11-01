package com.dev.smart_fridge.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name: String,
    val time: String
)