package com.example.avivhomeproject.property.data

import com.example.avivhomeproject.property.data.model.ListingsDataModel
import com.example.avivhomeproject.property.data.model.PropertyDataModel
import com.example.avivhomeproject.property.data.network.PropertyEndpoint
import javax.inject.Inject

class PropertyRemoteDataSource @Inject constructor(
    private val propertyEndpoint: PropertyEndpoint,
) {
    suspend fun listedProperties(): ListingsDataModel =
        propertyEndpoint.list()

    suspend fun getProperty(id: Int): PropertyDataModel =
        propertyEndpoint.getListing("$id")
}
