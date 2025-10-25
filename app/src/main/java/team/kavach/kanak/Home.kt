package team.kavach.kanak

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import team.kavach.kanak.Prices.PriceCard
import team.kavach.kanak.Scanner.ScannerCard
import team.kavach.kanak.Weather.Forecast.ForecastViewModel
import team.kavach.kanak.Weather.TemperatureCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    forecastViewModel: ForecastViewModel = viewModel()
) {
    Column (
        modifier.fillMaxSize().padding(horizontal = 10.dp).verticalScroll(scrollState),
    ) {
        TemperatureCard(forecastViewModel)
        ScannerCard()
        PriceCard()
        Spacer(Modifier.height(100.dp))
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}