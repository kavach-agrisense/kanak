package team.kavach.kanak.Navigation


import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Agriculture
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.toursafe.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.verticalGradientBrush

@Composable
fun NavBar(
    scrollState: ScrollState = rememberScrollState(),
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {


    val navBackStackEntry = navController.currentBackStackEntryAsState();

    var selectedIndex by remember { mutableIntStateOf(0) };
    val labels = listOf("Home", "Field", "Weather", "Alerts")
    val iconList = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Agriculture,
        Icons.Outlined.WbCloudy,
        Icons.Outlined.Notifications
    )
    val destinations = listOf(
        Screen.Home.route,
        Screen.Farm.route,
        Screen.Weather.route,
        Screen.Alert.route,
    )


    var scrolledDown by remember { mutableStateOf(true) }
    val lastScroll = remember { mutableIntStateOf(0) }

    LaunchedEffect(scrollState.value) {
        val current = scrollState.value
        scrolledDown = if (current < 100) {
            false
        } else if (current < lastScroll.value) {
            false
        } else {
            true
        }
        lastScroll.value = current
    }

    val radius = animateFloatAsState(
        if (scrolledDown) 0f else 100f,
        tween(250, 0, EaseOutCubic)
    )
    Column {


        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = NavBarShape(radius.value),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                ),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                border = BorderStroke(1.5.dp, Brush.verticalGradient(listOf(
                    MaterialTheme.colorScheme.surfaceContainer,
                    MaterialTheme.colorScheme.surfaceContainerLow,
                )))
            ) {
                Row(Modifier
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 0..3) {
                        if (i == 2) {
                            Spacer(Modifier.width(70.dp + (radius.value * 0.5).dp))
                        }
                        NavigationBarItem(
                            onClick = {
                                if (navBackStackEntry.value?.destination?.route != destinations[i]) {
                                    navController.navigate(destinations[i]) {
                                        popUpTo(Screen.Home.route) {  }
                                        launchSingleTop = true
                                    }
                                    selectedIndex = i;
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primary
                            ),
                            icon = { Icon(iconList[i], null) },
                            label = {
                                Text(
                                    labels[i],
                                    fontFamily = DMSansFontFamily,
                                    fontWeight = FontWeight.Light
                                )
                                    },
                            selected = navBackStackEntry.value?.destination?.route == destinations[i]
                        )
                    }
                }
            }
            SpecialButton({ }, !scrolledDown)
        }
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                .fillMaxWidth()
        )
    }
}

@Composable
fun NavItem(icon: ImageVector = Icons.Outlined.Home, name: String = "Home") {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null)
        Text(name)
    }
}



@Preview
@Composable
fun NavBarPreview() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        NavBar()
    }
}

@Preview
@Composable
fun PreviewNavItem() {
    Box(
        Modifier
            .size(100.dp)
            .background(Color.hsl(160f, 0.4f, 0.97f)),
        contentAlignment = Alignment.Center
    ) {
        NavItem()
    }
}



