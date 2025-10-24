package team.kavach.kanak.Weather.Forecast

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.toursafe.ui.theme.DMSansFontFamily
import com.google.gson.Gson
import team.kavach.kanak.Prices.PriceItem
import team.kavach.kanak.Prices.Separator
import team.kavach.kanak.Prices.UnitKnob
import team.kavach.kanak.ui.theme.inverseVerticalGradientBrush
import team.kavach.kanak.ui.theme.verticalGradientBrush
import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

private val dummyJson = "{\"location\":{\"name\":\"Narela\",\"region\":\"Delhi\",\"country\":\"India\",\"lat\":28.85,\"lon\":77.1,\"tz_id\":\"Asia/Kolkata\",\"localtime_epoch\":1761333621,\"localtime\":\"2025-10-25 00:50\"},\"current\":{\"last_updated_epoch\":1761333300,\"last_updated\":\"2025-10-25 00:45\",\"temp_c\":20.2,\"is_day\":0,\"condition\":{\"text\":\"Mist\",\"code\":1030},\"wind_kph\":4.0,\"wind_dir\":\"NE\",\"precip_mm\":0.0,\"humidity\":78},\"forecast\":{\"forecastday\":[{\"date_epoch\":1761350400,\"day\":{\"maxtemp_c\":31.2,\"mintemp_c\":21.2,\"avgtemp_c\":25.9,\"avghumidity\":32,\"daily_chance_of_rain\":0,\"condition\":{\"text\":\"Sunny\",\"code\":1000}},\"hour\":[{\"time_epoch\":1761330600,\"temp_c\":23.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":31,\"chance_of_rain\":0},{\"time_epoch\":1761334200,\"temp_c\":20.2,\"condition\":{\"text\":\"Mist\",\"code\":1030},\"humidity\":78,\"chance_of_rain\":0},{\"time_epoch\":1761337800,\"temp_c\":22.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":33,\"chance_of_rain\":0},{\"time_epoch\":1761341400,\"temp_c\":22.1,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":34,\"chance_of_rain\":0},{\"time_epoch\":1761345000,\"temp_c\":21.7,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761348600,\"temp_c\":21.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761352200,\"temp_c\":21.2,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":36,\"chance_of_rain\":0},{\"time_epoch\":1761355800,\"temp_c\":22.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":38,\"chance_of_rain\":0},{\"time_epoch\":1761359400,\"temp_c\":24.3,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":33,\"chance_of_rain\":0},{\"time_epoch\":1761363000,\"temp_c\":26.5,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":29,\"chance_of_rain\":0},{\"time_epoch\":1761366600,\"temp_c\":28.4,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":27,\"chance_of_rain\":0},{\"time_epoch\":1761370200,\"temp_c\":29.9,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":25,\"chance_of_rain\":0},{\"time_epoch\":1761373800,\"temp_c\":30.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":22,\"chance_of_rain\":0},{\"time_epoch\":1761377400,\"temp_c\":31.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":22,\"chance_of_rain\":0},{\"time_epoch\":1761381000,\"temp_c\":31.2,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":23,\"chance_of_rain\":0},{\"time_epoch\":1761384600,\"temp_c\":31.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":24,\"chance_of_rain\":0},{\"time_epoch\":1761388200,\"temp_c\":30.5,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":28,\"chance_of_rain\":0},{\"time_epoch\":1761391800,\"temp_c\":28.0,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":33,\"chance_of_rain\":0},{\"time_epoch\":1761395400,\"temp_c\":26.9,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":34,\"chance_of_rain\":0},{\"time_epoch\":1761399000,\"temp_c\":26.3,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":34,\"chance_of_rain\":0},{\"time_epoch\":1761402600,\"temp_c\":25.7,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761406200,\"temp_c\":25.2,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":37,\"chance_of_rain\":0},{\"time_epoch\":1761409800,\"temp_c\":24.8,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":38,\"chance_of_rain\":0},{\"time_epoch\":1761413400,\"temp_c\":24.3,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":39,\"chance_of_rain\":0}]},{\"date_epoch\":1761436800,\"day\":{\"maxtemp_c\":31.4,\"mintemp_c\":21.4,\"avgtemp_c\":25.9,\"avghumidity\":36,\"daily_chance_of_rain\":0,\"condition\":{\"text\":\"Sunny\",\"code\":1000}},\"hour\":[{\"time_epoch\":1761417000,\"temp_c\":23.8,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":40,\"chance_of_rain\":0},{\"time_epoch\":1761420600,\"temp_c\":23.2,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":42,\"chance_of_rain\":0},{\"time_epoch\":1761424200,\"temp_c\":22.8,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":43,\"chance_of_rain\":0},{\"time_epoch\":1761427800,\"temp_c\":22.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":44,\"chance_of_rain\":0},{\"time_epoch\":1761431400,\"temp_c\":22.0,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":45,\"chance_of_rain\":0},{\"time_epoch\":1761435000,\"temp_c\":21.7,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":46,\"chance_of_rain\":0},{\"time_epoch\":1761438600,\"temp_c\":21.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":47,\"chance_of_rain\":0},{\"time_epoch\":1761442200,\"temp_c\":22.4,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":47,\"chance_of_rain\":0},{\"time_epoch\":1761445800,\"temp_c\":24.5,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":41,\"chance_of_rain\":0},{\"time_epoch\":1761449400,\"temp_c\":26.7,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":36,\"chance_of_rain\":0},{\"time_epoch\":1761453000,\"temp_c\":28.4,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":32,\"chance_of_rain\":0},{\"time_epoch\":1761456600,\"temp_c\":29.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":30,\"chance_of_rain\":0},{\"time_epoch\":1761460200,\"temp_c\":30.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":27,\"chance_of_rain\":0},{\"time_epoch\":1761463800,\"temp_c\":31.0,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":25,\"chance_of_rain\":0},{\"time_epoch\":1761467400,\"temp_c\":31.4,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":24,\"chance_of_rain\":0},{\"time_epoch\":1761471000,\"temp_c\":31.3,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":24,\"chance_of_rain\":0},{\"time_epoch\":1761474600,\"temp_c\":30.5,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":27,\"chance_of_rain\":0},{\"time_epoch\":1761478200,\"temp_c\":27.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":32,\"chance_of_rain\":0},{\"time_epoch\":1761481800,\"temp_c\":26.6,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":33,\"chance_of_rain\":0},{\"time_epoch\":1761485400,\"temp_c\":25.9,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":34,\"chance_of_rain\":0},{\"time_epoch\":1761489000,\"temp_c\":25.3,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761492600,\"temp_c\":24.6,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":37,\"chance_of_rain\":0},{\"time_epoch\":1761496200,\"temp_c\":24.0,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":39,\"chance_of_rain\":0},{\"time_epoch\":1761499800,\"temp_c\":23.5,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":41,\"chance_of_rain\":0}]},{\"date_epoch\":1761523200,\"day\":{\"maxtemp_c\":31.0,\"mintemp_c\":20.7,\"avgtemp_c\":25.6,\"avghumidity\":37,\"daily_chance_of_rain\":0,\"condition\":{\"text\":\"Sunny\",\"code\":1000}},\"hour\":[{\"time_epoch\":1761503400,\"temp_c\":23.2,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":42,\"chance_of_rain\":0},{\"time_epoch\":1761507000,\"temp_c\":22.7,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":44,\"chance_of_rain\":0},{\"time_epoch\":1761510600,\"temp_c\":22.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":45,\"chance_of_rain\":0},{\"time_epoch\":1761514200,\"temp_c\":22.0,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":47,\"chance_of_rain\":0},{\"time_epoch\":1761517800,\"temp_c\":21.6,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":48,\"chance_of_rain\":0},{\"time_epoch\":1761521400,\"temp_c\":21.1,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":50,\"chance_of_rain\":0},{\"time_epoch\":1761525000,\"temp_c\":20.7,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":52,\"chance_of_rain\":0},{\"time_epoch\":1761528600,\"temp_c\":21.5,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":53,\"chance_of_rain\":0},{\"time_epoch\":1761532200,\"temp_c\":23.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":46,\"chance_of_rain\":0},{\"time_epoch\":1761535800,\"temp_c\":26.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":40,\"chance_of_rain\":0},{\"time_epoch\":1761539400,\"temp_c\":28.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761543000,\"temp_c\":29.6,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":30,\"chance_of_rain\":0},{\"time_epoch\":1761546600,\"temp_c\":30.4,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":27,\"chance_of_rain\":0},{\"time_epoch\":1761550200,\"temp_c\":30.9,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":24,\"chance_of_rain\":0},{\"time_epoch\":1761553800,\"temp_c\":31.0,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":23,\"chance_of_rain\":0},{\"time_epoch\":1761557400,\"temp_c\":30.8,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":23,\"chance_of_rain\":0},{\"time_epoch\":1761561000,\"temp_c\":30.1,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":27,\"chance_of_rain\":0},{\"time_epoch\":1761564600,\"temp_c\":27.6,\"condition\":{\"text\":\"Sunny\",\"code\":1000},\"humidity\":31,\"chance_of_rain\":0},{\"time_epoch\":1761568200,\"temp_c\":26.6,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":31,\"chance_of_rain\":0},{\"time_epoch\":1761571800,\"temp_c\":26.0,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":32,\"chance_of_rain\":0},{\"time_epoch\":1761575400,\"temp_c\":25.3,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":34,\"chance_of_rain\":0},{\"time_epoch\":1761579000,\"temp_c\":24.8,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":35,\"chance_of_rain\":0},{\"time_epoch\":1761582600,\"temp_c\":24.4,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":36,\"chance_of_rain\":0},{\"time_epoch\":1761586200,\"temp_c\":23.9,\"condition\":{\"text\":\"Clear \",\"code\":1000},\"humidity\":37,\"chance_of_rain\":0}]}]}}"
private val dummyForecastInfo = Gson().fromJson(dummyJson, ForecastInfo::class.java)

@Composable
fun ForecastScreen(modifier: Modifier, scrollState: ScrollState, viewModel: ForecastViewModel = viewModel()) {

    val forecastInfo = viewModel.forecastInfo.value;


    LaunchedEffect(Unit) {
        viewModel.fetchWeatherForecast()
    }
    Column (
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 10.dp)
    ){
        when {
            forecastInfo == null -> {

            } else -> {
                Card (
                    shape = RoundedCornerShape(30.dp),
                    border = BorderStroke(1.dp, verticalGradientBrush()),
                    modifier = Modifier.padding(5.dp, 10.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.elevatedCardElevation(4.dp)
                ) {
                    Column (
                        Modifier.padding(vertical = 25.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Text(
                            "Today Forecast",
                            fontFamily = DMSansFontFamily,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)
                        )
                        HourRow(forecastInfo, Modifier.horizontalScroll(rememberScrollState()))

                    }
                }
            ForecastDayCard(forecastInfo.forecast.forecastday)

            }
        }
    }
}


@Composable
fun HourRow (forecastInfo: ForecastInfo, modifier : Modifier = Modifier) {
    val hourList = (0 until 24).toList();

    Row (modifier.fillMaxWidth().padding(vertical = 10.dp)){

        Spacer(Modifier.width(20.dp))

        for (hour in 0 until hourList.size) {
            val timeEpoch = forecastInfo.forecast.forecastday[0].hour[hour].time_epoch;
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Card(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        elevation = CardDefaults.elevatedCardElevation(
                            if (isCurrentHour(timeEpoch)) 2.dp else 0.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCurrentHour(timeEpoch)) {
                                MaterialTheme.colorScheme.surfaceContainer
                            } else {
                                Color.Transparent
                            }
                        ),
                        border = if (isCurrentHour(timeEpoch)) BorderStroke(1.dp, verticalGradientBrush()) else null
                    ) {
                        Column(
                            Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "$hour.00",
                                fontFamily = DMSansFontFamily,
                                color = if (isCurrentHour(timeEpoch)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "${forecastInfo.forecast.forecastday[0].hour[hour].temp_c.roundToInt()}°C",
                                fontSize = 18.sp,
                                fontWeight = if (isCurrentHour(timeEpoch)) FontWeight.SemiBold else FontWeight.Medium,
                                fontFamily = DMSansFontFamily
                            )
                            Spacer(Modifier.height(10.dp))
                            Icon(
                                forecastInfo.getWeatherIcon(hour),
                                null,
                                tint = if (isCurrentHour(timeEpoch)) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                    HourChart(forecastInfo.forecast.forecastday[0].hour[hour])
                }
            }
    }
    Spacer(Modifier.width(20.dp))
}

@Composable
fun HourChart(hourInfo : Hour = dummyForecastInfo.forecast.forecastday[0].hour[0]) {
    val tempHeight = hourInfo.temp_c/45f
    val humidHeight = hourInfo.humidity/100f;
    val precptHeight = hourInfo.chance_of_rain/100f;
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
fun ForecastDayCard(forcastDayList : List<Forecastday> = dummyForecastInfo.forecast.forecastday) {
    Card (
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
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
                            fontSize = 26.sp
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
                            fontWeight = FontWeight.SemiBold,
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
                            fontWeight = FontWeight.SemiBold,
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

@Preview
@Composable
fun ForeCastDayCardPreview() {
    ForecastDayCard()
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

fun isCurrentHour(timeEpoch: Int): Boolean {
    return (System.currentTimeMillis()/1000 - timeEpoch).absoluteValue < 3600 &&
            System.currentTimeMillis()/1000 > timeEpoch
}

@Preview("Hour Row Preview")
@Composable
fun HourRowPreview() {
    Box(Modifier.background(MaterialTheme.colorScheme.background)) {
        HourRow(dummyForecastInfo, Modifier.horizontalScroll(rememberScrollState()).padding(20.dp))
    }

}
