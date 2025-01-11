package com.example.avivhomeproject.property.data.model

import com.google.gson.annotations.SerializedName

data class ListingsDataModel(
    @SerializedName("items") val items: List<PropertyDataModel>
)