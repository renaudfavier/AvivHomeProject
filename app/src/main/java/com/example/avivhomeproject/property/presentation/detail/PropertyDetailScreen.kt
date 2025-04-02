package com.example.avivhomeproject.property.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.avivhomeproject.core.presentation.component.CenteredErrorText
import com.example.avivhomeproject.core.presentation.component.CenteredInfiniteCircularProgressIndicator
import com.example.avivhomeproject.core.ui.theme.AvivTheme
import com.example.avivhomeproject.property.presentation.component.PropertyDimensionsRow
import com.example.avivhomeproject.property.presentation.component.PropertyImage

@Composable
fun PropertyDetailScreen(
    uiModel: PropertyDetailUiModel,
    onBackButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
        .fillMaxSize()
) {
    when (uiModel) {
        is PropertyDetailUiModel.Content -> Content(uiModel.details)
        is PropertyDetailUiModel.Error -> CenteredErrorText(uiModel.message, modifier.fillMaxSize())
        PropertyDetailUiModel.Loading -> CenteredInfiniteCircularProgressIndicator(modifier.fillMaxSize())
    }
    BackButton(
        onClick = onBackButtonPressed,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 18.dp)
            .size(48.dp)
    )
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = IconButton(
    onClick = onClick,
    modifier = modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = CircleShape,
        color = Color.White,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "back icon",
            modifier = Modifier.padding(6.dp),
        )
    }
}

@Composable
private fun Content(
    uiModel: DetailedPropertyUiModel,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier.fillMaxSize()
) {
    val windowWidthSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    if (windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        Column(Modifier.fillMaxSize()) {
            PropertyImage(
                imageUrl = uiModel.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 11f),
            )
            Description(uiModel)
        }
    } else {
        Row(Modifier.fillMaxSize()) {
            PropertyImage(
                imageUrl = uiModel.imageUrl,
                modifier = Modifier
                    .heightIn(max = 400.dp)
                    .fillMaxHeight()
                    .aspectRatio(4 / 3f),
            )
            Description(uiModel)
        }
    }
}

@Composable
private fun Description(
    uiModel: DetailedPropertyUiModel,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 24.dp)
) {
    Text(
        text = uiModel.offerType,
        modifier = Modifier.padding(bottom = 12.dp),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
    )
    PropertyDimensionsRow(
        size = uiModel.size,
        roomCount = uiModel.roomCount,
        bedroomCount = uiModel.bedroomCount,
        modifier = Modifier.padding(bottom = 12.dp)
    )
    Text(
        text = uiModel.city,
        modifier = Modifier.padding(bottom = 24.dp),
        color = Color.DarkGray,
        style = MaterialTheme.typography.bodyLarge
    )
    PriceRow(
        price = uiModel.price,
        pricePerSquareMeter = uiModel.pricePerSquareMeter
    )
}

@Composable
private fun PriceRow(
    price: String,
    pricePerSquareMeter: String,
) = Row(verticalAlignment = Alignment.Bottom) {
    Text(
        text = "$price €",
        modifier = Modifier.padding(end = 8.dp),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
    )
    Text(
        text = "$pricePerSquareMeter € / m²",
        color = Color.Gray,
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview(device = "spec:parent=pixel_5,orientation=portrait")
@Composable
private fun ContentPreviewPortrait() {
    AvivTheme {
        PropertyDetailScreen(
            PropertyDetailUiModel.Content(
                DetailedPropertyUiModel(
                    imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                    offerType = "Achat appartement",
                    size = "83",
                    roomCount = 3,
                    bedroomCount = 2,
                    city = "Bordeaux",
                    price = "850 000",
                    pricePerSquareMeter = "10 252"
                )
            ),
            onBackButtonPressed = {},
        )
    }
}

@Preview(device = "spec:parent=pixel_9_pro_fold")
@Composable
private fun ContentPreviewPortraitProFold() {
    AvivTheme {
        PropertyDetailScreen(
            PropertyDetailUiModel.Content(
                DetailedPropertyUiModel(
                    imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                    offerType = "Achat appartement",
                    size = "83",
                    roomCount = 3,
                    bedroomCount = 2,
                    city = "Bordeaux",
                    price = "850 000",
                    pricePerSquareMeter = "10 252"
                )
            ),
            onBackButtonPressed = {},
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun ContentPreviewLandscape() {
    AvivTheme {
        PropertyDetailScreen(
            PropertyDetailUiModel.Content(
                DetailedPropertyUiModel(
                    imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
                    offerType = "Achat appartement",
                    size = "83",
                    roomCount = 3,
                    bedroomCount = 2,
                    city = "Bordeaux",
                    price = "850 000",
                    pricePerSquareMeter = "10 252"
                )
            ),
            onBackButtonPressed = {},
        )
    }
}

@Preview()
@Composable
private fun LoadingPreview() {
    AvivTheme {
        PropertyDetailScreen(
            PropertyDetailUiModel.Loading,
            onBackButtonPressed = {},
        )
    }
}

@Preview()
@Composable
private fun ErrorPreview() {
    AvivTheme {
        PropertyDetailScreen(
            PropertyDetailUiModel.Error("there was an error"),
            onBackButtonPressed = {},
        )
    }
}

