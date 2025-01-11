package com.example.avivhomeproject.property.presentation.detail

import com.example.avivhomeproject.core.presentation.util.formatWithSpaceBetweenThousands
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.Property
import javax.inject.Inject

class DetailUiModelMapper @Inject constructor() {

    fun map(property: Property) = property.mapToUiModel()

    private fun Property.mapToUiModel() = DetailedPropertyUiModel(
        imageUrl = imageUrl,
        offerType = offerType.mapToDisplayString(),
        size = "$area",
        roomCount = rooms,
        bedroomCount = bedrooms,
        city = city,
        price = price.formatWithSpaceBetweenThousands(),
        pricePerSquareMeter = (price / area).formatWithSpaceBetweenThousands()
    )

    private fun OfferType.mapToDisplayString() = when(this) {
        OfferType.OFFER_TYPE_3 -> "Vente Appartement"
        OfferType.OFFER_TYPE_2 -> "Location Appartement"
        OfferType.OFFER_TYPE_1 -> "Achat Appartement"
        OfferType.OFFER_TYPE_4 -> "Location Saisonière"
    }
}