package com.example.avivhomeproject.property.presentation.detail

import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals


class DetailUiModelMapperTest @Inject constructor() {

    private val mapper = DetailUiModelMapper()

    @Test
    fun `map should correctly transform Property to DetailedPropertyUiModel`() {
        val property = Property(
            id = 1,
            city = "Paris",
            price = 500000.0,
            area = 100.0,
            bedrooms = 2,
            rooms = 4,
            imageUrl = "http://example.com/property.jpg",
            type = PropertyType.VILLA,
            offerType = OfferType.OFFER_TYPE_1,
            professionalType = ProfessionalType.EXPLORE
        )

        val result = mapper.map(property)

        val expected = DetailedPropertyUiModel(
            imageUrl = "http://example.com/property.jpg",
            offerType = "Achat Appartement",
            size = "100.0",
            roomCount = 4,
            bedroomCount = 2,
            city = "Paris",
            price = "500 000",
            pricePerSquareMeter = "5 000"
        )

        assertEquals(expected, result)
    }

    @Test
    fun `map should handle null values for optional fields`() {
        val property = Property(
            id = 2,
            city = "Lyon",
            price = 250000.0,
            area = 50.0,
            bedrooms = null,
            rooms = null,
            imageUrl = null,
            type = PropertyType.VILLA,
            offerType = OfferType.OFFER_TYPE_2,
            professionalType = ProfessionalType.OWNERS
        )

        val result = mapper.map(property)

        val expected = DetailedPropertyUiModel(
            imageUrl = null,
            offerType = "Location Appartement",
            size = "50.0",
            roomCount = null,
            bedroomCount = null,
            city = "Lyon",
            price = "250 000",
            pricePerSquareMeter = "5 000"
        )

        assertEquals(expected, result)
    }
}