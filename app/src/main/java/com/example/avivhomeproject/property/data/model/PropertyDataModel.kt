package com.example.avivhomeproject.property.data.model

import com.google.gson.annotations.SerializedName

data class PropertyDataModel(
    @SerializedName("bedrooms") val bedrooms: Int?,
    @SerializedName("city") val city: String,
    @SerializedName("id") val id: Int,
    @SerializedName("area") val area: Double,
    @SerializedName("url") val imageUrl: String?,
    @SerializedName("price") val price: Double,
    @SerializedName("professional") val professional: String,
    @SerializedName("propertyType") val propertyType: String,
    @SerializedName("offerType") val offerType: Int,
    @SerializedName("rooms") val rooms: Int?,
)


