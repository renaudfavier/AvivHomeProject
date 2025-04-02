package com.example.avivhomeproject.property.presentation.list

import com.example.avivhomeproject.property.domain.PropertyRepository
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var propertyRepository: PropertyRepository
    private lateinit var mapper: ListUiModelMapper

    private lateinit var viewModel: ListViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        propertyRepository = mock()
        mapper = mock()
        viewModel = ListViewModel(propertyRepository, mapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel is created, state is Loading`() = runTest {
        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        assertEquals(PropertyListUiModel.Loading, viewModel.uiState.value)
    }

    @Test
    fun `when we collect the viewModel state, it calls the repository`() = runTest {
        //When
        given(propertyRepository.getListedProperties())
            .willReturn(Result.success(listOf(createProperty())))
        given(mapper.map(any()))
            .willReturn(persistentListOf())

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        verify(propertyRepository, times(1)).getListedProperties()
    }


    @Test
    fun `when repository gives listings, map them into a Content`() = runTest {
        //When
        given(propertyRepository.getListedProperties())
            .willReturn(Result.success(listOf(createProperty())))
        given(mapper.map(any()))
            .willReturn(persistentListOf())

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        assert(viewModel.uiState.value is PropertyListUiModel.Content)
    }

    @Test
    fun `when repository gives error, map them into a error`() = runTest {
        //When
        given(propertyRepository.getListedProperties())
            .willReturn(Result.failure(Exception()))

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        assert(viewModel.uiState.value is PropertyListUiModel.Error)
    }

    private fun createProperty(
        id: Int = 1,
        city: String = "Default City",
        price: Double = 100000.0,
        area: Double = 50.0,
        bedrooms: Int? = 2,
        rooms: Int? = 3,
        imageUrl: String? = "http://example.com/default.jpg",
        type: PropertyType = PropertyType.VILLA,
        offerType: OfferType = OfferType.OFFER_TYPE_1,
        professionalType: ProfessionalType = ProfessionalType.EXPLORE
    ) = Property(
        id = id,
        city = city,
        price = price,
        area = area,
        bedrooms = bedrooms,
        rooms = rooms,
        imageUrl = imageUrl,
        type = type,
        offerType = offerType,
        professionalType = professionalType
    )
}
