package com.example.avivhomeproject.property.data.network

import com.example.avivhomeproject.property.data.model.ListingsDataModel
import com.example.avivhomeproject.property.data.model.PropertyDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PropertyEndpoint {
    @GET("/listings.json")
    suspend fun list(): ListingsDataModel

    @GET("listings/{listingId}.json")
    suspend fun getListing(
        @Path("listingId") listingId: String
    ): PropertyDataModel
}