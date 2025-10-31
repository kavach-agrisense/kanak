package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import team.kavach.kanak.Prices.Separator
import team.kavach.kanak.Weather.HourlyModel.ForecastHour
import team.kavach.kanak.Weather.HourlyModel.HourlyForecast
import team.kavach.kanak.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.verticalGradientBrush
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.hours

private val dummyJson : String = "{\n" +
        "  \"forecastHours\": [\n" +
        "    {\n" +
        "      \"displayDateTime\": {\n" +
        "        \"hours\": 2\n" +
        "      },\n" +
        "      \"weatherCondition\": {\n" +
        "        \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/clear\",\n" +
        "        \"description\": {\n" +
        "          \"text\": \"Clear\",\n" +
        "          \"languageCode\": \"en\"\n" +
        "        },\n" +
        "        \"type\": \"CLEAR\"\n" +
        "      },\n" +
        "      \"temperature\": {\n" +
        "        \"unit\": \"CELSIUS\",\n" +
        "        \"degrees\": 13.3\n" +
        "      },\n" +
        "      \"precipitation\": {\n" +
        "        \"probability\": {\n" +
        "          \"type\": \"RAIN\",\n" +
        "          \"percent\": 0\n" +
        "        }\n" +
        "      },\n" +
        "      \"relativeHumidity\": 71\n" +
        "    },\n" +
        "    {\n" +
        "      \"displayDateTime\": {\n" +
        "        \"hours\": 3\n" +
        "      },\n" +
        "      \"weatherCondition\": {\n" +
        "        \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/clear\",\n" +
        "        \"description\": {\n" +
        "          \"text\": \"Clear\",\n" +
        "          \"languageCode\": \"en\"\n" +
        "        },\n" +
        "        \"type\": \"CLEAR\"\n" +
        "      },\n" +
        "      \"temperature\": {\n" +
        "        \"unit\": \"CELSIUS\",\n" +
        "        \"degrees\": 13\n" +
        "      },\n" +
        "      \"precipitation\": {\n" +
        "        \"probability\": {\n" +
        "          \"type\": \"RAIN\",\n" +
        "          \"percent\": 0\n" +
        "        }\n" +
        "      },\n" +
        "      \"relativeHumidity\": 70\n" +
        "    }\n" +
        "  ]\n" +
        "}\n"
private val dummyForecastInfo = Gson().fromJson(dummyJson, HourlyForecast::class.java)

@Composable
fun ForecastScreen(modifier: Modifier, scrollState: ScrollState, viewModel: WeatherViewModel = viewModel()) {

    val forecastInfo = viewModel.hourlyForecastInfo.value;

    Column (
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 10.dp)
            .padding(bottom = 100.dp)
    ){
        HourlyForecastCard(viewModel)
        DailyForecastCard(viewModel)
    }
}


@Composable
fun HourlyForecastCard(viewModel : WeatherViewModel = viewModel()) {

    val forecastInfo = viewModel.hourlyForecastInfo.value

    when {
        forecastInfo == null -> {
            LoadingForecast()
        }

        else -> {
            Card(
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(1.dp, verticalGradientBrush()),
                modifier = Modifier.padding(5.dp, 10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Column(
                    Modifier.padding(vertical = 25.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        "Today's Forecast",
                        fontFamily = DMSansFontFamily,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                    )

                    val rowScrollState = rememberScrollState();
                    HourRow(forecastInfo, Modifier.horizontalScroll(rowScrollState))

                    ColorIndicators()
                    /*ScrollToCurrentHour(rowScrollState, forecastInfo.forecast.forecastday[0].hour)*/
                }
            }
            /*ForecastDayCard(forecastInfo.forecast.forecastday)*/
        }
    }
}

@Composable
fun ColorIndicators() {
    Row (
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Card (
                modifier = Modifier.size(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ){  }
            Text("Temp",
                fontSize = 12.sp
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Card (
                modifier = Modifier.size(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
            ){  }
            Text(
                "Humidity",
                fontSize = 14.sp
            )
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Card (
                modifier = Modifier.size(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
            ){  }
            Text(
                "Precipitation",
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun HourRow (forecastInfo: HourlyForecast, modifier : Modifier = Modifier) {
    val hourList = (0 until 24).toList();

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {


        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {

            Spacer(Modifier.width(20.dp))
            for (hour in 0 until hourList.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        elevation = CardDefaults.elevatedCardElevation(
                            if (isCurrentHour(hour)) 2.dp else 0.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCurrentHour(hour)) {
                                MaterialTheme.colorScheme.surfaceContainer
                            } else {
                                Color.Transparent
                            }
                        ),
                        border = if (isCurrentHour(hour)) BorderStroke(
                            1.dp,
                            verticalGradientBrush()
                        ) else null
                    ) {
                        Column(
                            Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "$hour.00",
                                fontFamily = DMSansFontFamily,
                                color = if (isCurrentHour(hour)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "${forecastInfo.forecastHours[hour].temperature.degrees.roundToInt()}°C",
                                fontSize = 18.sp,
                                fontWeight = if (isCurrentHour(hour)) FontWeight.SemiBold else FontWeight.Medium,
                                fontFamily = DMSansFontFamily
                            )
                            Spacer(Modifier.height(10.dp))
                            Icon(
                                forecastInfo.getWeatherIcon(hour),
                                null,
                                tint = if (isCurrentHour(hour)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground
                            )
                            /*Image(
                                painterResource(forecastInfo.getWeatherIcon(hour)),
                                null,
                                modifier = Modifier.size(27.dp)
                            )*/
                        }
                    }

                    HourChart(forecastInfo.forecastHours[hour])
                }
            }
            Spacer(Modifier.width(20.dp))
        }

        PrecipitationChart(forecastInfo)
    }
    Spacer(Modifier.width(20.dp))
}

@Composable
fun HourChart(hourInfo : ForecastHour = dummyForecastInfo.forecastHours[0]) {
    val tempHeight = hourInfo.temperature.degrees/45f
    val humidHeight = hourInfo.relativeHumidity/100f;
    val precptHeight = hourInfo.precipitation.probability.percent/100f
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.height(200.dp)
    ) {
        Card (
            modifier = Modifier.size(12.dp,(tempHeight * 200).dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        ){}
        Card (
            modifier = Modifier.size(12.dp, (humidHeight * 200).dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
        ){ }
        Card (
            modifier = Modifier.size(12.dp, (precptHeight * 200).dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ){ }
    }
}


@Composable
fun PrecipitationChart(forecastInfo: HourlyForecast) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .drawWithCache {
                val width = size.width
                val height = size.height

                val vertices = FloatArray(forecastInfo.forecastHours.size * 2)
                for (i in 0 until forecastInfo.forecastHours.size) {
                    vertices[i * 2] = i / 24f * width
                    vertices[i * 2 + 1] =
                        height - forecastInfo.forecastHours[i].precipitation.probability.percent / 100f * height
                }

                Log.e("VERTICES", vertices.contentToString())

                val polygon = RoundedPolygon(
                    vertices,
                    rounding = CornerRounding(30f, 0.6f)
                )

                val path = polygon.toPath().asComposePath();
                onDrawBehind {
                    drawPath(
                        path, Color.Black,
                        style = Stroke(1f)
                    )
                }
            }
    )
}
/*
@Composable
fun ForecastDayCard(forcastDayList : List<Forecastday> = dummyForecastInfo.forecast.forecastday) {
    Card (
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp),
        border = BorderStroke(1.dp, verticalGradientBrush())
    ) {
        Column (
            Modifier.padding(25.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Upcoming Days",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = DMSansFontFamily,
            )
            Spacer(Modifier.height(10.dp))
            for (i in 0 until forcastDayList.size) {
                val day = forcastDayList[i].day;
                Row  (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            getWeekdayNameFromEpoch(forcastDayList[i].date_epoch.toLong())
                        )
                        Text(
                            "${day.avgtemp_c.roundToInt()}°C",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            "Humidity",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            "${day.avghumidity}%",
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )

                    }
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            "Precipitation",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            "${day.daily_chance_of_rain}%",
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                    }
                }
                if (i != 2) {
                    Separator()
                }
            }
        }
    }
}
*/

@Preview
@Composable
fun ForeCastDayCardPreview() {
    /*ForecastDayCard()*/
}

@Preview
@Composable
fun HourChartPreview() {
    HourChart()
}

fun getWeekdayNameFromEpoch(epochSeconds: Long): String {
    val dayOfWeek = Instant.ofEpochSecond(epochSeconds)
        .atZone(ZoneId.systemDefault())
        .dayOfWeek
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}

fun isCurrentHour(currentHour : Int): Boolean {
    return LocalDateTime.now().hour == currentHour
}

@Preview("Hour Row Preview")
@Composable
fun HourRowPreview() {
    Box(Modifier.background(MaterialTheme.colorScheme.background)) {
        HourRow(dummyForecastInfo, Modifier
            .horizontalScroll(rememberScrollState())
            .padding(20.dp))
    }

}
