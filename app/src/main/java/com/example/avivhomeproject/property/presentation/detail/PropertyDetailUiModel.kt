package com.example.avivhomeproject.property.presentation.detail

sealed interface PropertyDetailUiModel {

    data object Loading : PropertyDetailUiModel

    data class Content(val details: DetailedPropertyUiModel) : PropertyDetailUiModel

    data class Error(val message: String) : PropertyDetailUiModel
}

data class DetailedPropertyUiModel(
    val imageUrl: String?,
    val offerType: String,
    val size: String,
    val roomCount: Int?,
    val bedroomCount: Int?,
    val city: String,
    val price: String,
    val pricePerSquareMeter: String,
)
