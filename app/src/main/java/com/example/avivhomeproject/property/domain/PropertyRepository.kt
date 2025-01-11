package com.example.avivhomeproject.property.domain

import com.example.avivhomeproject.property.domain.model.Property

interface PropertyRepository {
    suspend fun getListedProperties(): Result<List<Property>>
    suspend fun getProperty(id: Int): Result<Property>
}