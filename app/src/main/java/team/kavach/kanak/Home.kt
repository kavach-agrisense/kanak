package team.kavach.kanak

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.kavach.kanak.Scanner.ScannerCard
import team.kavach.kanak.Weather.TemperatureCard

@Composable
fun HomeScreen(
    modifier : Modifier = Modifier
) {
    Column (
        modifier.fillMaxSize().padding(horizontal = 10.dp),
    ) {
        TemperatureCard()
        ScannerCard()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}