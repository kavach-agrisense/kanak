package team.kavach.kanak.NavBar


import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.kavach.kanak.R

@Composable
fun NavBar() {
    
    var selectedIndex by remember { mutableIntStateOf(0) };
    val labelList = listOf("Home", "Alerts")
    val iconList = listOf(Icons.Rounded.Home, Icons.Rounded.Notifications)
    var scrolledDown by remember { mutableStateOf(true) }
    val radius = animateFloatAsState(
        if (scrolledDown) 0f else 100f,
        tween(250, 0, EaseOutCubic)
    )
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.navigationBarsPadding()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = NavBarShape(radius.value),
            colors = CardDefaults.cardColors(
                containerColor = Color.hsl(160f, 0.4f, 0.95f)
            ),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            border = BorderStroke(
                1.5.dp, Brush.verticalGradient(
                    listOf(
                        Color.hsl(160f, 0.4f, 1f),
                        Color.hsl(160f, 0.4f, 0.95f)
                    )
                )
            )
        ) {
            Row(Modifier.padding(horizontal = 10.dp)) {
                Button(
                    onClick = { scrolledDown = true },
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp)
                ) { }
                Button(onClick = { scrolledDown = false }) { }

            }
        }
        SpecialButton({ scrolledDown = !scrolledDown }, !scrolledDown)
    }
}

@Composable
fun NavItem() {

}


@Composable
fun SpecialButton(onClick: () -> Unit, active: Boolean) {

    val easeOutBack = null
    val onActive by animateFloatAsState(
        if (active) 1f else 0f,
        tween(250, 0, EaseOutBack)
    )

    Column {
        Button(
            onClick,
            shape = CircleShape,
            modifier = Modifier.size(50.dp + 30.dp * onActive),
            elevation = ButtonDefaults.buttonElevation(2.dp * onActive),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.hsl(160f, 0.4f, 0.95f)
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Image(
                painterResource(if (active) R.drawable.robo else R.drawable.robo_material),
                null,
            )
        }
        Spacer(Modifier.height(20.dp + 30.dp * onActive))
    }
}

@Preview
@Composable
fun SpecialButtonPreview() {
    Box(modifier = Modifier
        .size(100.dp)
        .background(Color.hsl(160f, 0.4f, 0.9f))) {
        SpecialButton({}, true)
    }
}

@Preview
@Composable
fun NavBarPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NavBar()
    }
}

/*val HelpButtonShape = GenericShape { size, _ ->
    val radius = 30f;

    moveTo(size.width/2 - radius, radius)

    quadraticTo(
        size.width/2, 0f,
        size.width/2 + radius, radius
    )

    lineTo(size.width - radius, size.height/2 - radius)

    quadraticTo(
        size.width, size.height/2,
        size.width - radius, size.height/2 + radius
    )

    lineTo(size.width/2 + radius, size.height - radius)

    quadraticTo(size.width/2, size.height,
        size.width/2 - radius, size.height - radius
        )

    lineTo(radius, size.height/2 + radius)

    quadraticTo(
        0f, size.width / 2,
        radius, size.height/2 - radius
    )

    lineTo(size.width/2 - radius, radius)
}*/

fun NavBarShape(radius: Float): GenericShape {
    return GenericShape { size, _ ->
        val width = size.width
        val height = size.height

        // central dip parameters
        val dipRadius = radius
        val dipCenterX = width / 2

        moveTo(0f, 0f)
        lineTo(dipCenterX - dipRadius - 60f, 0f)
        // create smooth dip

        quadraticTo(
            dipCenterX - dipRadius, radius * 0.05f,
            dipCenterX - dipRadius / 2, dipRadius * 2 / 3,
        )

        quadraticTo(
            dipCenterX, dipRadius * 1.1f,
            dipCenterX + dipRadius / 2, dipRadius * 2 / 3,
        )


        quadraticTo(
            dipCenterX + dipRadius, radius * 0.05f,
            dipCenterX + dipRadius + 60f, 0f
        )
        /*quadraticTo(
            dipCenterX, dipRadius * 1.4f,
            dipCenterX + dipRadius, 0f
        )*/

        lineTo(width, 0f)
        lineTo(width, height)
        lineTo(0f, height)
        close()
    }
}
