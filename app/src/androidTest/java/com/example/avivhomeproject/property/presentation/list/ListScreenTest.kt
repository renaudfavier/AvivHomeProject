package com.example.avivhomeproject.property.presentation.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import org.junit.Rule
import org.junit.Test

class ListScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun whenLoading_showsLoadingIndicator() {
        // Given
        rule.setContent {
            PropertyListScreen(
                uiModel = PropertyListUiModel.Loading,
                onPropertyClick = {}
            )
        }

        // Then
        rule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
    }

    @Test
    fun whenError_showsErrorMessage() {
        // Given
        val errorMessage = "error message"
        rule.setContent {
            PropertyListScreen(
                uiModel = PropertyListUiModel.Error(errorMessage),
                onPropertyClick = {}
            )
        }

        // Then
        rule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun whenContent_showsProperties() {
        // Given
        val property = createUiModel(
            type = "Apartment",
            price = "850 000",
            cityName = "Bordeaux"
        )

        rule.setContent {
            PropertyListScreen(
                uiModel = PropertyListUiModel.Content(listOf(property)),
                onPropertyClick = {}
            )
        }

        // Then
        rule.onNodeWithText("Listings").assertIsDisplayed()
        rule.onNodeWithText("Apartment").assertIsDisplayed()
        rule.onNodeWithText("850 000").assertIsDisplayed()
        rule.onNodeWithText("Bordeaux").assertIsDisplayed()
    }

    @Test
    fun tapProperty_launchNavigationEvent() {

        // Given
        var propertyId: Int? = null
        val onPropertyClick: (Int) -> Unit = { propertyId = it }
        val content = PropertyListUiModel.Content(
            listOf(createUiModel(id = 1, type = "testType"), createUiModel(id = 2))
        )

        rule.setContent { PropertyListScreen(content, onPropertyClick) }

        // When
        rule.onNode(
            hasAnyChild(hasText("testType"))
                    and
                    hasClickAction(),
            useUnmergedTree = true
        ).performClick()

        // Then
        assert(propertyId == 1)
    }

    @Test
    fun whenMultipleProperties_allAreDisplayed() {
        // Given
        val properties = listOf(
            createUiModel(cityName = "Bordeaux"),
            createUiModel(cityName = "Paris"),
            createUiModel(cityName = "Bayonne"),
            createUiModel(cityName = "Nice"),
        )

        rule.setContent {
            PropertyListScreen(
                uiModel = PropertyListUiModel.Content(properties),
                onPropertyClick = {}
            )
        }

        // Then
        rule.onNodeWithText("Bordeaux").assertIsDisplayed()
        rule.onNodeWithText("Paris").assertIsDisplayed()
        rule.onNode(hasScrollToIndexAction()).performScrollToIndex(3)
        rule.onNodeWithText("Bayonne").assertIsDisplayed()
        rule.onNodeWithText("Nice").assertIsDisplayed()
    }

    private fun createUiModel(
        id: Int = 1,
        type: String = "Apartment",
        size: String = "50 sqm",
        roomCount: Int? = 2,
        bedroomCount: Int? = 1,
        cityName: String = "New York",
        imageUrl: String? = null,
        price: String = "$1,000"
    ) = PropertyListItemUiModel(
        id = id,
        type = type,
        size = size,
        roomCount = roomCount,
        bedroomCount = bedroomCount,
        cityName = cityName,
        imageUrl = imageUrl,
        price = price
    )
}
