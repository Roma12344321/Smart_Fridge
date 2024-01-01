package com.dev.smart_fridge.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val time: String
) {
    @Ignore
    constructor(name: String, time: String) : this(0, name, time)
}