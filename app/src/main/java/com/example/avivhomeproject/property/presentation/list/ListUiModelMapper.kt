package com.example.avivhomeproject.property.presentation.list

import com.example.avivhomeproject.core.presentation.util.formatWithSpaceBetweenThousands
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class ListUiModelMapper @Inject constructor() {

    fun map(properties: List<Property>) = properties.map { it.mapToUiModel() }.toImmutableList()

    private fun Property.mapToUiModel() = PropertyListItemUiModel(
        id = id,
        cityName = city,
        imageUrl = imageUrl,
        price = "${price.formatWithSpaceBetweenThousands()} €",
        type = type.mapToString(),
        size = "$area",
        roomCount = rooms,
        bedroomCount = bedrooms,
    )

    private fun PropertyType.mapToString() = when(this) {
        PropertyType.VILLA -> "Villa"
    }
}
