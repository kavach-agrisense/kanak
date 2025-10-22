package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Thunderstorm
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import team.kavach.kanak.R;

@Composable
fun WeatherRow(viewModel : WeatherViewModel) {
    val componentList = listOf(TemperatureCard(viewModel), )
}

@Composable
fun TemperatureCard(viewModel : WeatherViewModel = viewModel()) {

    val weatherInfo = viewModel.weatherInfo.value;

    LaunchedEffect(Unit) {
        viewModel.fetchWeatherInfo()
    }

    Card (
        modifier = Modifier.height(270.dp).fillMaxWidth().padding(5.dp, 10.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.hsl(160f, 0.4f, 0.97f),
            contentColor = Color.hsl(160f, 0.4f, 0.1f)
        ),
        border = BorderStroke(
            1.dp,
            Brush.verticalGradient(listOf(
                Color.hsl(160f, 0.4f, 1f),
                Color.hsl(160f, 0.4f, 0.95f)
            ))),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
    ) {
        when {
            weatherInfo == null -> {
                FetchingWeather()
            } else -> {
                FetchedWeatherInfo(weatherInfo)
            }
        }
    }
}


@Composable
fun HumidityCard(weatherInfo: WeatherInfo) {
    Card {
        Row {
            Icon(
                Icons.Outlined.WaterDrop,
                "Humidity"
            );
        }
    }
}

@Composable
fun FetchingWeather () {

    Row(
        Modifier.fillMaxSize().padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        Spacer(Modifier.width(10.dp))
        Text("Loading weather...")
    }
}

@Composable
fun FetchedWeatherInfo(weatherInfo: WeatherInfo) {
    Log.e("BRO IMAGE", "https:${weatherInfo.current.condition.icon}")
    val icons = listOf(Icons.Rounded.WaterDrop, Icons.Rounded.Air, Icons.Rounded.Thunderstorm);
    val entities = listOf("Humidity", "Wind", "Precipitation");
    val values = listOf(
        "${weatherInfo.current.humidity}%",
        "${weatherInfo.current.wind_kph} km/h",
        "${weatherInfo.current.precip_mm} mm",
    );

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row (
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            Image(
                painter = painterResource(weatherInfo.icon()),
                null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.width(20.dp))
            Column (horizontalAlignment = Alignment.Start){
                Text (
                    "${weatherInfo.current.temp_c}°C",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    softWrap = false,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(weatherInfo.current.condition.text, color = Color.hsl(160f, 0.4f, 0.1f))
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            for (i : Int in 0..2) {
                Column (
                    Modifier.width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        icons[i],
                        null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        values[i].toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        entities[i],
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWeatherFetch() {
    Column() {
        Card(
            modifier = Modifier.height(100.dp).width(250.dp),
            shape = RoundedCornerShape(50.dp)
        ) {
            FetchingWeather()
        }
        Card(
            modifier = Modifier.height(100.dp).width(250.dp),
            shape = RoundedCornerShape(50.dp)
        ) {

        }
    }
}