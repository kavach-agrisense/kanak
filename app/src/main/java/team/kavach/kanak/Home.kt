package team.kavach.kanak

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.kavach.kanak.Prices.PriceCard
import team.kavach.kanak.Scanner.ScannerCard
import team.kavach.kanak.Weather.TemperatureCard

@Composable
fun HomeScreen(
    modifier : Modifier = Modifier,
    scrollState : ScrollState = rememberScrollState()
) {
    Column (
        modifier.fillMaxSize().padding(horizontal = 10.dp).verticalScroll(scrollState),
    ) {
        TemperatureCard()
        ScannerCard()
        PriceCard()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}