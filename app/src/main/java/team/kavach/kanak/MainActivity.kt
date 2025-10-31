package team.kavach.kanak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SupervisedUserCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import team.kavach.kanak.Navigation.NavBar
import team.kavach.kanak.Navigation.Screen
import team.kavach.kanak.Weather.fetchAndReturnWeatherViewModel
import team.kavach.kanak.Weather.ForecastScreen
import team.kavach.kanak.ui.popSlideFadeIn
import team.kavach.kanak.ui.popSlideFadeOut
import team.kavach.kanak.ui.slideFadeIn
import team.kavach.kanak.ui.slideFadeOut
import team.kavach.kanak.ui.theme.ApplicationTheme
import team.kavach.kanak.ui.theme.DMSansFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme () {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    val homeScrollState = rememberScrollState();
    val farmScrollState = rememberScrollState();
    val alertScrollState = rememberScrollState();
    val weatherScrollState = rememberScrollState();

    val mainNavController = rememberNavController();

    val navBackStackEntry by mainNavController.currentBackStackEntryAsState();

    val forecastViewModel = fetchAndReturnWeatherViewModel();

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
        },
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            NavHost(
                mainNavController, startDestination = Screen.Home.route,
                enterTransition = { slideFadeIn() },
                exitTransition = { slideFadeOut() },
                popEnterTransition = { popSlideFadeIn() },
                popExitTransition = { popSlideFadeOut() }
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        scrollState = homeScrollState,
                        fetchAndReturnWeatherViewModel(),
                        mainNavController
                    )
                }
                composable (Screen.Alert.route) {
                    AlertScreen(alertScrollState, modifier = Modifier.padding(innerPadding))
                }
                composable (Screen.Weather.route) {
                    ForecastScreen(Modifier.padding(innerPadding), weatherScrollState, forecastViewModel)
                }
                composable (Screen.Farm.route) {
                    FarmScreen(modifier = Modifier.padding(innerPadding), farmScrollState)
                }
            }
            NavBar(
                scrollState = when (navBackStackEntry?.destination?.route) {
                    Screen.Home.route -> homeScrollState
                    Screen.Alert.route -> alertScrollState
                    Screen.Weather.route -> weatherScrollState
                    Screen.Farm.route -> farmScrollState
                    else -> rememberScrollState()
                },
                navController = mainNavController
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        Modifier
            .background(Color(0xfffbf1c7))
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar (){
    TopAppBar(title = {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ){
            Text("Kanak", fontFamily = DMSansFontFamily)
            Icon(Icons.Rounded.SupervisedUserCircle, null)
        }
    },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background))
}