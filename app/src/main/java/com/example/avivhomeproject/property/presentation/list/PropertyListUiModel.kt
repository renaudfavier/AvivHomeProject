package com.example.avivhomeproject.property.presentation.list

sealed interface PropertyListUiModel {

    data object Loading : PropertyListUiModel

    data class Content(val properties: List<PropertyListItemUiModel>) : PropertyListUiModel

    data class Error(val message: String) : PropertyListUiModel
}

data class PropertyListItemUiModel(
    val id: Int,
    val type: String,
    val size: String,
    val roomCount: Int?,
    val bedroomCount: Int?,
    val cityName: String,
    val imageUrl: String?,
    val price: String,
)
