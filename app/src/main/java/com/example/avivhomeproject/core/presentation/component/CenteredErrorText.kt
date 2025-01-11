package com.example.avivhomeproject.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avivhomeproject.core.ui.theme.AvivTheme

@Composable
fun CenteredErrorText(
    message: String,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    Text(message, Modifier.fillMaxWidth().padding(horizontal = 12.dp))
}

@Preview
@Composable
private fun ErrorPrev() {
    AvivTheme {
        CenteredErrorText("there was an error", Modifier.fillMaxSize())
    }
}
