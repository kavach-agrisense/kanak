package team.kavach.kanak.Weather

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeatherScreen(scrollState: ScrollState = rememberScrollState(), modifier: Modifier = Modifier) {
    Column (
        modifier.fillMaxSize()
    ){

    }
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}