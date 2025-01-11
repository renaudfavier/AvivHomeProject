package com.example.avivhomeproject.property.data

import com.example.avivhomeproject.core.data.ParseEnumException
import com.example.avivhomeproject.property.data.mapper.PropertyMapper
import com.example.avivhomeproject.property.data.model.ListingsDataModel
import com.example.avivhomeproject.property.data.model.PropertyDataModel
import com.example.avivhomeproject.property.domain.PropertyRepository
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import kotlin.test.assertEquals


@RunWith(MockitoJUnitRunner::class)
class PropertyRepositoryImplTest {

    @Mock
    private lateinit var propertyRemoteDataSource: PropertyRemoteDataSource

    private lateinit var propertyRepository: PropertyRepository

    @Before
    fun setUp() {
        propertyRepository = PropertyRepositoryImpl(propertyRemoteDataSource)
    }

    @Test
    fun `when remote is unreachable, repository gives a failure response containing the exception`() = runTest {

        val exception = RuntimeException("Network error")
        given(propertyRemoteDataSource.listedProperties())
            .willThrow(exception)

        // When
        val result = propertyRepository.getListedProperties()

        // Then
        assert(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `when remote gives valid listings, repository gives a successful result with mapped data in it`() = runTest {
        // Given
        given(propertyRemoteDataSource.listedProperties()).willReturn(fakeListings)

        // When
        val result = propertyRepository.getListedProperties()

        // Then
        val expected = Result.success(fakePropertiesMapped)
        assertEquals(expected, result)
    }

    @Test
    fun `when remote gives valid property, repository gives a successful result with mapped data in it`() = runTest {
        // Given
        given(propertyRemoteDataSource.getProperty(1)).willReturn(fakeListings.items[0])

        // When
        val result = propertyRepository.getProperty(1)

        // Then
        val expected = Result.success(fakePropertiesMapped[0])
        assertEquals(expected, result)
    }

    @Test
    fun `when mapper gives a parse ParseEnumException, repository gives a failure response`() = runTest {

        //Setup with a mocked mapper
        val propertyMapper: PropertyMapper = mock()
        propertyRepository = PropertyRepositoryImpl(propertyRemoteDataSource, propertyMapper)

        // Given
        val parseException = ParseEnumException("field")
        given(propertyMapper.mapToEntity(any())).willThrow(parseException)
        given(propertyRemoteDataSource.listedProperties()).willReturn(fakeListings)

        // When
        val result = propertyRepository.getListedProperties()

        // Then
        assert(result.isFailure)
        assertEquals(parseException, result.exceptionOrNull())
    }

    private val fakeListings = ListingsDataModel(
        items = listOf(
            PropertyDataModel(
                bedrooms = 4,
                city = "Villers-sur-Mer",
                id = 1,
                area = 250.0,
                imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                price = 1500000.0,
                professional = "GSL EXPLORE",
                propertyType = "Maison - Villa",
                offerType = 1,
                rooms = 8,
            ),
            PropertyDataModel(
                bedrooms = null,
                city = "Bordeaux",
                id = 3,
                area = 550.0,
                imageUrl = null,
                price = 3000000.0,
                professional = "GSL OWNERS",
                propertyType = "Maison - Villa",
                offerType = 1,
                rooms = null
            )
        )
    )

    private val fakePropertiesMapped = listOf(
        Property(
            id = 1,
            city = "Villers-sur-Mer",
            price = 1500000.0,
            area = 250.0,
            rooms = 8,
            bedrooms = 4,
            imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            professionalType = ProfessionalType.EXPLORE,
            offerType = OfferType.OFFER_TYPE_1,
            type = PropertyType.VILLA,
            ),
        Property(
            id = 3,
            city = "Bordeaux",
            area = 550.0,
            price = 3000000.0,
            rooms = null,
            bedrooms = null,
            imageUrl = null,
            type = PropertyType.VILLA,
            offerType = OfferType.OFFER_TYPE_1,
            professionalType = ProfessionalType.OWNERS,
        )
    )
}
