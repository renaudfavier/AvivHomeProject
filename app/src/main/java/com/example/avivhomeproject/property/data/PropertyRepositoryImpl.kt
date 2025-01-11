package com.example.avivhomeproject.property.data

import com.example.avivhomeproject.property.data.mapper.PropertyMapper
import com.example.avivhomeproject.property.domain.PropertyRepository
import com.example.avivhomeproject.property.domain.model.Property
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val propertyRemoteDataSource: PropertyRemoteDataSource,
    private val propertyMapper: PropertyMapper = PropertyMapper()
): PropertyRepository {

    override suspend fun getListedProperties(): Result<List<Property>> {
        return try {
            val properties = propertyRemoteDataSource.listedProperties()
            Result.success(properties.items.map { propertyMapper.mapToEntity(it) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProperty(id: Int): Result<Property> {
        return try {
            val property = propertyRemoteDataSource.getProperty(id)
            Result.success(propertyMapper.mapToEntity(property))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
