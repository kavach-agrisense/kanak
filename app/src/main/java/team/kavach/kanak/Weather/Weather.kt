package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.svg.SvgDecoder
import team.kavach.kanak.Navigation.Screen
import team.kavach.kanak.R
import team.kavach.kanak.Weather.CurrentModel.WeatherCondition
import team.kavach.kanak.Weather.CurrentModel.WeatherInfo
import team.kavach.kanak.ui.theme.verticalGradientBrush

@Composable
fun WeatherRow(viewModel: WeatherViewModel) {
    val componentList = listOf(TemperatureCard(viewModel), )
}

@Composable
fun TemperatureCard(
    viewModel: WeatherViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {

    val weatherInfo = viewModel.weatherInfo.value

    Card (
        modifier = Modifier
            .height(270.dp)
            .fillMaxWidth()
            .padding(5.dp, 10.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            1.dp,
            verticalGradientBrush()
        ),
        onClick = {
            navController.navigate(Screen.Weather.route)
        },
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
fun FetchingWeather () {

    Row(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
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
    val icons = listOf(Icons.Rounded.WaterDrop, Icons.Rounded.Air, Icons.Rounded.Thunderstorm);
    val entities = listOf("Humidity", "Wind", "Precipitation");
    val values = listOf(
        "${weatherInfo.relativeHumidity}%",
        "${weatherInfo.wind.speed.value} km/h",
        "${weatherInfo.precipitation.probability.percent}%",
    );

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            Column {

            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(weatherInfo.getWeatherIcon()),
                    null,
                    modifier = Modifier.size(60.dp)
                )
                // ConditionIcon(weatherInfo.weatherCondition, modifier = Modifier.size(70.dp))
                Spacer(Modifier.width(20.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "${weatherInfo.temperature.degrees}°C",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        softWrap = false,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        weatherInfo.weatherCondition.description.text,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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

@Composable
fun ConditionIcon(condition: WeatherCondition, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context).components {
            add(SvgDecoder.Factory())
        }.build()
    }
    AsyncImage(
        model = if (isSystemInDarkTheme()) "${condition.iconBaseUri}_dark.svg" else "${condition.iconBaseUri}.svg",
        contentDescription = condition.description.text,
        imageLoader = imageLoader,
        modifier = modifier,
        placeholder = painterResource(R.drawable.cloud),
        onError = { it ->
            Log.e("ConditionIcon", "Error Loading Icon ${it.result}")
        },
        onSuccess = {
            Log.d("ConditionIcon", "Success!!")
        }
    )
}

@Preview
@Composable
fun PreviewWeatherFetch() {
    Column() {
        Card(
            modifier = Modifier
                .height(100.dp)
                .width(250.dp),
            shape = RoundedCornerShape(50.dp)
        ) {
            FetchingWeather()
        }
        Card(
            modifier = Modifier
                .height(100.dp)
                .width(250.dp),
            shape = RoundedCornerShape(50.dp)
        ) {

        }
    }
}