package com.example.avivhomeproject.property.presentation.list

import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals

class ListUiModelMapperTest @Inject constructor() {

    private val mapper = ListUiModelMapper()

    @Test
    fun `map should correctly transform a list of Property to a list of PropertyListItemUiModel`() {
        val properties = listOf(
            Property(
                id = 1,
                city = "New York",
                price = 500000.0,
                area = 150.3,
                bedrooms = 3,
                rooms = 5,
                imageUrl = "http://example.com/property1.jpg",
                type = PropertyType.VILLA,
                offerType = OfferType.OFFER_TYPE_1,
                professionalType = ProfessionalType.EXPLORE
            )
        )

        val result = mapper.map(properties)

        val expected = listOf(
            PropertyListItemUiModel(
                id = 1,
                cityName = "New York",
                imageUrl = "http://example.com/property1.jpg",
                price = "500 000 €",
                type = "Villa",
                size = "150.3",
                roomCount = 5,
                bedroomCount = 3
            )
        )

        assertEquals(expected, result)
    }

    @Test
    fun `map should handle null values for optional fields`() {
        val properties = listOf(
            Property(
                id = 2,
                city = "Los Angeles",
                price = 250000.0,
                area = 100.0,
                bedrooms = null,
                rooms = null,
                imageUrl = null,
                type = PropertyType.VILLA,
                offerType = OfferType.OFFER_TYPE_1,
                professionalType = ProfessionalType.OWNERS
            )
        )

        val result = mapper.map(properties)

        val expected = listOf(
            PropertyListItemUiModel(
                id = 2,
                cityName = "Los Angeles",
                imageUrl = null,
                price = "250 000 €",
                type = "Villa",
                size = "100.0",
                roomCount = null,
                bedroomCount = null
            )
        )

        assertEquals(expected, result)
    }
}