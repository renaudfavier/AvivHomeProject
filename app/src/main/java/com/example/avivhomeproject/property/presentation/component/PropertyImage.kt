package com.example.avivhomeproject.property.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.SubcomposeAsyncImage
import com.example.avivhomeproject.R
import com.example.avivhomeproject.core.presentation.component.ErrorImage

@Composable
fun PropertyImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) = if (imageUrl.isNullOrEmpty()) {
    Image(
        painter = painterResource(R.drawable.placeholder),
        contentDescription = "placeholder",
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
} else {
    PropertyImageNotNull(imageUrl, modifier)
}

@Composable
private fun PropertyImageNotNull(
    imageUrl: String,
    modifier: Modifier = Modifier
) = SubcomposeAsyncImage(
    model = imageUrl,
    contentDescription = stringResource(R.string.content_description_property_detail_photo),
    modifier = modifier,
    loading = {
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = "placeholder",
            contentScale = ContentScale.Crop,
        )
    },
    error = {
        if (LocalInspectionMode.current) {
            Image(
                painter = painterResource(R.drawable.debug_photo),
                contentDescription = "placeholder",
                contentScale = ContentScale.Crop,
            )
        } else {
            ErrorImage()
        }
    },
    contentScale = ContentScale.Crop,
)
