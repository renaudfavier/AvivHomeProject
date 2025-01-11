package com.example.avivhomeproject.property.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.avivhomeproject.R

@Composable
fun PropertyDimensionsRow(
    size: String,
    roomCount: Int?,
    bedroomCount: Int?,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier
) {
    LabelText(text = "$size m²")
    roomCount?.let {
        MiddleDot()
        LabelText(text = pluralStringResource(R.plurals.numberOfRooms, roomCount, roomCount))
    }
    bedroomCount?.let {
        MiddleDot()
        LabelText(
            text = pluralStringResource(
                R.plurals.numberOfBedrooms,
                bedroomCount,
                bedroomCount
            )
        )
    }
}

@Composable
private fun MiddleDot() = LabelText(" · ")

@Composable
private fun LabelText(text: String) = Text(
    text = text,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.labelMedium
)