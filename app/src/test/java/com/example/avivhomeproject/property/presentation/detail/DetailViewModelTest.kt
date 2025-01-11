package com.example.avivhomeproject.property.presentation.detail

import com.example.avivhomeproject.PropertyDetailRoute
import com.example.avivhomeproject.property.domain.PropertyRepository
import com.example.avivhomeproject.property.domain.model.OfferType
import com.example.avivhomeproject.property.domain.model.ProfessionalType
import com.example.avivhomeproject.property.domain.model.Property
import com.example.avivhomeproject.property.domain.model.PropertyType
import com.example.avivhomeproject.rule.SavedStateHandleRule
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
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private val route = PropertyDetailRoute(1)

    // https://issuetracker.google.com/issues/349807172?pli=1
    @get:Rule
    val savedStateHandleRule = SavedStateHandleRule(route)

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var propertyRepository: PropertyRepository
    private val mapper = DetailUiModelMapper()

    private lateinit var viewModel: DetailViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        propertyRepository = mock()
        viewModel = DetailViewModel(
            savedStateHandle = savedStateHandleRule.savedStateHandleMock,
            propertyRepository = propertyRepository,
            mapper = mapper,
        )
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

        assertEquals(PropertyDetailUiModel.Loading, viewModel.uiState.value)
    }

    @Test
    fun `when we start collecting the viewModel state, subject calls the repository with the correct Id`() = runTest {
        //When
        given(propertyRepository.getProperty(route.id))
            .willReturn(Result.success(createProperty()))

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        verify(propertyRepository, times(1)).getProperty(route.id)
    }


    @Test
    fun `when repository returns a property, map it into a Content`() = runTest {
        //When
        given(propertyRepository.getProperty(route.id))
            .willReturn(Result.success(createProperty()))

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        assert(viewModel.uiState.value is PropertyDetailUiModel.Content)
    }

    @Test
    fun `when repository gives error, map them into a error`() = runTest {
        //When
        given(propertyRepository.getProperty(route.id))
            .willReturn(Result.failure(Exception()))

        // Create an empty collector for the StateFlow
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        delay(100)
        assert(viewModel.uiState.value is PropertyDetailUiModel.Error)
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