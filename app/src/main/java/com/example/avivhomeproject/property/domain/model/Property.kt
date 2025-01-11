package com.example.avivhomeproject.property.domain.model

data class Property(
    val id: Int,
    val city: String,
    val price: Double,
    val area: Double,
    val bedrooms: Int?,
    val rooms: Int?,
    val imageUrl: String?,
    val type: PropertyType,
    val offerType: OfferType,
    val professionalType: ProfessionalType,
)