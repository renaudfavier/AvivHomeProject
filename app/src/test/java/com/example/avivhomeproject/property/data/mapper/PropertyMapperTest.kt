package com.example.avivhomeproject.property.data.mapper

import com.example.avivhomeproject.core.data.ParseEnumException
import com.example.avivhomeproject.property.data.model.PropertyDataModel
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.PropertyType
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PropertyMapperTest {

    private lateinit var mapper: PropertyMapper

    @Before
    fun setup() {
        mapper = PropertyMapper()
    }

    @Test
    fun `mapToEntity maps all fields correctly`() {
        // Given
        val dataModel = PropertyDataModel(
            id = 1,
            propertyType = "Maison - Villa",
            offerType = 1,
            city = "Paris",
            price = 1500000.0,
            area = 250.0,
            bedrooms = 4,
            rooms = 8,
            imageUrl = "https://example.com/image.jpg",
            professional = "GSL EXPLORE"
        )

        // When
        val result = mapper.mapToEntity(dataModel)

        // Then
        assertEquals(1, result.id)
        assertEquals(PropertyType.VILLA, result.type)
        assertEquals(OfferType.OFFER_TYPE_1, result.offerType)
        assertEquals("Paris", result.city)
        assertEquals(1500000.0, result.price, 0.0)
        assertEquals(250.0, result.area, 0.0)
        assertEquals(4, result.bedrooms)
        assertEquals(8, result.rooms)
        assertEquals("https://example.com/image.jpg", result.imageUrl)
        assertEquals(ProfessionalType.EXPLORE, result.professionalType)
    }

    @Test
    fun `mapToEntity handles different offer types correctly`() {
        // Given
        val dataModelOffer1 = createDataModel(offerType = 1)
        val dataModelOffer2 = createDataModel(offerType = 2)
        val dataModelOffer3 = createDataModel(offerType = 3)
        val dataModelOffer4 = createDataModel(offerType = 4)

        // Then
        assertEquals(OfferType.OFFER_TYPE_1, mapper.mapToEntity(dataModelOffer1).offerType)
        assertEquals(OfferType.OFFER_TYPE_2, mapper.mapToEntity(dataModelOffer2).offerType)
        assertEquals(OfferType.OFFER_TYPE_3, mapper.mapToEntity(dataModelOffer3).offerType)
        assertEquals(OfferType.OFFER_TYPE_4, mapper.mapToEntity(dataModelOffer4).offerType)

        assertEquals(4, OfferType.entries.size)
    }

    @Test
    fun `mapToEntity handles different professional types correctly`() {
        assertEquals(
            ProfessionalType.EXPLORE,
            mapper.mapToEntity(createDataModel(professional = "GSL EXPLORE")).professionalType
        )
        assertEquals(
            ProfessionalType.OWNERS,
            mapper.mapToEntity(createDataModel(professional = "GSL OWNERS")).professionalType
        )
        assertEquals(
            ProfessionalType.CONTACTING,
            mapper.mapToEntity(createDataModel(professional = "GSL CONTACTING")).professionalType
        )
        assertEquals(
            ProfessionalType.STICKINESS,
            mapper.mapToEntity(createDataModel(professional = "GSL STICKINESS")).professionalType
        )
    }

    @Test(expected = ParseEnumException::class)
    fun `mapToEntity throws exception for invalid offer type`() {
        mapper.mapToEntity(createDataModel(offerType = 999))
    }

    @Test(expected = ParseEnumException::class)
    fun `mapToEntity throws exception for invalid property type`() {
        mapper.mapToEntity(createDataModel(propertyType = "Invalid Type"))
    }

    @Test(expected = ParseEnumException::class)
    fun `mapToEntity throws exception for invalid professional type`() {
        mapper.mapToEntity(createDataModel(professional = "Invalid Professional"))
    }

    private fun createDataModel(
        id: Int = 1,
        propertyType: String = "Maison - Villa",
        offerType: Int = 1,
        city: String = "Paris",
        price: Double = 1500000.0,
        area: Double = 250.0,
        bedrooms: Int? = 4,
        rooms: Int? = 8,
        imageUrl: String? = "https://example.com/image.jpg",
        professional: String = "GSL EXPLORE"
    ) = PropertyDataModel(
        id = id,
        propertyType = propertyType,
        offerType = offerType,
        city = city,
        price = price,
        area = area,
        bedrooms = bedrooms,
        rooms = rooms,
        imageUrl = imageUrl,
        professional = professional
    )
}