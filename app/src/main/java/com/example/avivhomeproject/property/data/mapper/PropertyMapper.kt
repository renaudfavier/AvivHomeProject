package com.example.avivhomeproject.property.data.mapper

import com.example.avivhomeproject.core.data.ParseEnumException
import com.example.avivhomeproject.property.data.model.PropertyDataModel
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import javax.inject.Inject

class PropertyMapper @Inject constructor() {
    fun mapToEntity(dataModel: PropertyDataModel) = Property(
        id = dataModel.id,
        type = dataModel.propertyType.mapPropertyType(),
        offerType = dataModel.offerType.mapOfferType(),
        city = dataModel.city,
        price = dataModel.price,
        area = dataModel.area,
        bedrooms = dataModel.bedrooms,
        rooms = dataModel.rooms,
        imageUrl = dataModel.imageUrl,
        professionalType = dataModel.professional.mapProfessionalType(),
    )

    //Ask what offer means
    private fun Int.mapOfferType(): OfferType = when(this) {
        1 -> OfferType.OFFER_TYPE_1
        2 -> OfferType.OFFER_TYPE_2
        3 -> OfferType.OFFER_TYPE_3
        4 -> OfferType.OFFER_TYPE_4
        else -> throw ParseEnumException(unknownValue = "$this")
    }

    private fun String.mapPropertyType(): PropertyType = when(this) {
        "Maison - Villa" -> PropertyType.VILLA
        else -> throw ParseEnumException(unknownValue = this)
    }

    private fun String.mapProfessionalType() = when(this) {
        "GSL EXPLORE" -> ProfessionalType.EXPLORE
        "GSL OWNERS" -> ProfessionalType.OWNERS
        "GSL CONTACTING" -> ProfessionalType.CONTACTING
        "GSL STICKINESS" -> ProfessionalType.STICKINESS
        else -> throw ParseEnumException(unknownValue = this)
    }
}
