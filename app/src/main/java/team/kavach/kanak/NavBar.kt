package team.kavach.kanak

import android.graphics.drawable.Icon
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toursafe.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.*;

@Composable
fun NavBar() {
    
    var selectedIndex by remember { mutableIntStateOf(0) };
    val labelList = listOf("Home", "Alerts")
    val iconList = listOf(Icons.Rounded.Home, Icons.Rounded.Notifications)
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surfaceDim
    )
    {
        for (i in 0 until labelList.size ) {
            NavigationBarItem(
                selected = selectedIndex == i,
                onClick = { selectedIndex = i },
                icon = { Icon(iconList[i], null) },
                label = { Text(labelList[i], fontFamily = DMSansFontFamily) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.tertiary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,

                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,


                    )
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    NavBar();
}