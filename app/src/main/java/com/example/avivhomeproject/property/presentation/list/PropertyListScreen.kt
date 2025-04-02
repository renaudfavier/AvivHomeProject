package com.example.avivhomeproject.property.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avivhomeproject.core.presentation.component.CenteredErrorText
import com.example.avivhomeproject.core.presentation.component.CenteredInfiniteCircularProgressIndicator
import com.example.avivhomeproject.core.ui.theme.AvivTheme
import com.example.avivhomeproject.property.presentation.component.PropertyDimensionsRow
import com.example.avivhomeproject.property.presentation.component.PropertyImage
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PropertyListScreen(
    uiModel: PropertyListUiModel,
    onPropertyClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(top = 24.dp)
) {
    Text(
        text = "Listings",
        modifier = Modifier.padding(horizontal = 24.dp),
        style = MaterialTheme.typography.headlineLarge
    )

    when (uiModel) {
        is PropertyListUiModel.Content -> Content(uiModel.properties, onPropertyClick, modifier)
        is PropertyListUiModel.Error -> CenteredErrorText(uiModel.message, modifier.fillMaxSize())
        PropertyListUiModel.Loading -> CenteredInfiniteCircularProgressIndicator(modifier.fillMaxSize())
    }
}

@Composable
private fun Content(
    properties: List<PropertyListItemUiModel>,
    onPropertyClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) = LazyColumn(modifier = modifier) {
    items(properties) { property ->
        PropertyItem(
            propertyListItemUiModel = property,
            modifier = Modifier
                .clickable { onPropertyClick(property.id) }
                .padding(vertical = 36.dp, horizontal = 24.dp)
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp))
    }
}

@Composable
private fun PropertyItem(
    propertyListItemUiModel: PropertyListItemUiModel,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier
) {
    Surface(
        modifier = Modifier.padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        PropertyImage(
            imageUrl = propertyListItemUiModel.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 11f)
        )
    }
    Text(
        text = propertyListItemUiModel.price,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
    )
    Text(text = propertyListItemUiModel.type)
    PropertyDimensionsRow(
        size = propertyListItemUiModel.size,
        roomCount = propertyListItemUiModel.roomCount,
        bedroomCount = propertyListItemUiModel.bedroomCount
    )
    Text(
        text = propertyListItemUiModel.cityName,
        color = Color.DarkGray,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Preview
@Composable
private fun ContentPreview() {
    AvivTheme {

        val previewUiModel = PropertyListItemUiModel(
            id = 1,
            type = "Appartment",
            size = "83",
            roomCount = 3,
            bedroomCount = 2,
            cityName = "Bordeaux",
            imageUrl = null,
            price = "850 000",
        )

        PropertyListScreen(
            uiModel = PropertyListUiModel.Content(
                persistentListOf(previewUiModel, previewUiModel, previewUiModel)
            ),
            onPropertyClick = { },
        )
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    AvivTheme {
        PropertyListScreen(
            uiModel = PropertyListUiModel.Error("message"),
            onPropertyClick = { },
        )
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    AvivTheme {
        PropertyListScreen(
            uiModel = PropertyListUiModel.Loading,
            onPropertyClick = { },
        )
    }
}
