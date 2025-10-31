package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.svg.SvgDecoder
import com.google.gson.Gson
import team.kavach.kanak.Prices.Separator
import team.kavach.kanak.R
import team.kavach.kanak.Weather.DailyModel.WeatherCondition
import team.kavach.kanak.Weather.DailyModel.DailyForecastInfo
import team.kavach.kanak.Weather.DailyModel.ForecastDay
import team.kavach.kanak.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.verticalGradientBrush


private val _dummyJson = "{\n" +
        "  \"forecastDays\": [\n" +
        "    {\n" +
        "      \"displayDate\": {\n" +
        "        \"year\": 2025,\n" +
        "        \"month\": 10,\n" +
        "        \"day\": 30\n" +
        "      },\n" +
        "      \"daytimeForecast\": {\n" +
        "        \"weatherCondition\": {\n" +
        "          \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/mostly_sunny\"\n" +
        "        },\n" +
        "        \"relativeHumidity\": 84,\n" +
        "        \"precipitation\": {\n" +
        "          \"probability\": {\n" +
        "            \"percent\": 10\n" +
        "          }\n" +
        "        }\n" +
        "      },\n" +
        "      \"nighttimeForecast\": {\n" +
        "        \"weatherCondition\": {\n" +
        "          \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/mostly_cloudy_night\"\n" +
        "        },\n" +
        "        \"relativeHumidity\": 92,\n" +
        "        \"precipitation\": {\n" +
        "          \"probability\": {\n" +
        "            \"percent\": 10\n" +
        "          }\n" +
        "        }\n" +
        "      },\n" +
        "      \"maxTemperature\": {\n" +
        "        \"degrees\": 18\n" +
        "      },\n" +
        "      \"minTemperature\": {\n" +
        "        \"degrees\": 11.2\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"displayDate\": {\n" +
        "        \"year\": 2025,\n" +
        "        \"month\": 10,\n" +
        "        \"day\": 31\n" +
        "      },\n" +
        "      \"daytimeForecast\": {\n" +
        "        \"weatherCondition\": {\n" +
        "          \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/partly_cloudy\"\n" +
        "        },\n" +
        "        \"relativeHumidity\": 77,\n" +
        "        \"precipitation\": {\n" +
        "          \"probability\": {\n" +
        "            \"percent\": 15\n" +
        "          }\n" +
        "        }\n" +
        "      },\n" +
        "      \"nighttimeForecast\": {\n" +
        "        \"weatherCondition\": {\n" +
        "          \"iconBaseUri\": \"https://maps.gstatic.com/weather/v1/mostly_clear\"\n" +
        "        },\n" +
        "        \"relativeHumidity\": 86,\n" +
        "        \"precipitation\": {\n" +
        "          \"probability\": {\n" +
        "            \"percent\": 0\n" +
        "          }\n" +
        "        }\n" +
        "      },\n" +
        "      \"maxTemperature\": {\n" +
        "        \"degrees\": 20.3\n" +
        "      },\n" +
        "      \"minTemperature\": {\n" +
        "        \"degrees\": 11.3\n" +
        "      }\n" +
        "    }\n" +
        "  ]\n" +
        "}\n"

private val dummyForecastInfo = Gson().fromJson(_dummyJson, DailyForecastInfo::class.java)

@Composable
fun DailyForecastCard(viewModel: WeatherViewModel = viewModel()) {
    val forecastInfo = viewModel.dailyForecastInfo.value;
    val expanded = remember { mutableStateOf(true) }

    Card (
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, verticalGradientBrush()),
        modifier = Modifier.padding(5.dp, 10.dp).defaultMinSize(minHeight = 200.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = { expanded.value = !expanded.value }
    ){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp).fillMaxWidth()
        ){
            if (forecastInfo == null) {
                LoadingForecast()
            } else {
                CompactedForecastinfo(forecastInfo, expanded)
            }
        }
    }
}

@Composable
fun LoadingForecast() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
        Text("Loading Weather forecast")
    }
}

@Composable
fun CompactedForecastinfo(forecastInfo: DailyForecastInfo, expanded: MutableState<Boolean>) {

    val viewDay = remember { mutableStateOf(true) }

    val lastIndex = if (!expanded.value) 3 else forecastInfo.forecastDays.size
    Log.d("EXPANDED?" ,"${forecastInfo.forecastDays.size}")
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "This Week",
            fontSize = 30.sp,
            fontFamily = DMSansFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
        DayNightToggle(viewDay)
    }
    Spacer(modifier = Modifier.height(15.dp))

    for (i in 0 until lastIndex) {
        DailyForecastItem(forecastInfo.forecastDays[i], viewDay.value)
        if (i < lastIndex - 1) Separator()
    }
}

@Composable
fun DayNightToggle(enabled : MutableState<Boolean>) {

    val shape = RoundedCornerShape(12.dp);
    val offset = animateDpAsState(
        targetValue = if (enabled.value) 0.dp else 48.dp,
        tween(200, 0, EaseOutBack)
    );
    Box(
        modifier = Modifier.width(100.dp)
            .height(50.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { enabled.value = !enabled.value }
            )
    ) {
        Button(
            onClick = { enabled.value = !enabled.value },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(50.dp).offset(x = offset.value),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            elevation = ButtonDefaults.buttonElevation(3.dp),
            border = BorderStroke(1.dp, verticalGradientBrush())
        ) {}
        Row (
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(Icons.Outlined.WbSunny, "Show Day")
            Icon(Icons.Outlined.Bedtime, "Show Night")
        }
    }
}

@Composable
fun DailyForecastItem(day: ForecastDay, viewDay : Boolean = true) {
    val condition : WeatherCondition
    val temperature : Int
    val humidity : Int
    val precipitation : Int
    if (viewDay) {
        temperature = day.maxTemperature.degrees.toInt()
        humidity = day.daytimeForecast.relativeHumidity
        precipitation = day.daytimeForecast.precipitation.probability.percent
        condition = day.daytimeForecast.weatherCondition
    } else {
        temperature = day.minTemperature.degrees.toInt()
        humidity = day.nighttimeForecast.relativeHumidity
        precipitation = day.nighttimeForecast.precipitation.probability.percent
        condition = day.nighttimeForecast.weatherCondition
    }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(10.dp).height(80.dp)
    ){
        Column (
            modifier = Modifier.width(80.dp).fillMaxHeight(),
        ){
            Text(
                day.displayDate.day(),
                fontWeight = FontWeight.Medium,
            )
            Spacer(Modifier.height(15.dp))
            Text(
                "$temperature°C",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        ConditionIcon(condition, modifier = Modifier.size(40.dp))

        Column (
            modifier = Modifier.fillMaxHeight(),
        ) {
            Text(
                "Precipitation",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Spacer(Modifier.height(15.dp))
            Text(
                "$precipitation%",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column (
            modifier = Modifier.fillMaxHeight(),
        ){
            Text(
                "Humidity",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
            Spacer(Modifier.height(15.dp))
            Text(
                "$humidity%",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
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
        contentDescription = null,
        imageLoader = imageLoader,
        modifier = modifier.alpha(if (isSystemInDarkTheme()) 0.8f else 1f),
        placeholder = painterResource(R.drawable.cloud),
        onError = { it ->
            Log.e("ConditionIcon", "Error Loading Icon ${it.result}")
        },
        onSuccess = {
            Log.d("ConditionIcon", "Success!!")
        }
    )
}

@Preview("Day Night Toggle")
@Composable
fun TogglePreview() {
    DayNightToggle(remember { mutableStateOf(true) })
}

@Preview
@Composable
fun DailyForecastPreview() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        CompactedForecastinfo(dummyForecastInfo, remember { mutableStateOf(true) })
    }
}



@Preview("Loading Animation")
@Composable
fun LoadingForecastPreview() {
    LoadingForecast()
}