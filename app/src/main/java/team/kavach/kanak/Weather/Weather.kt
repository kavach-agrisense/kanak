package team.kavach.kanak.Weather

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun WeatherCard() {
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Hi", fontSize = 24.sp)
    }
}