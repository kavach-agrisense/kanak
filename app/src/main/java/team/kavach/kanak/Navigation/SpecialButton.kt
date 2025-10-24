package team.kavach.kanak.Navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.kavach.kanak.R
import team.kavach.kanak.ui.theme.verticalGradientBrush

@Composable
fun SpecialButton(onClick: () -> Unit, active: Boolean) {
    val onActive by animateFloatAsState(
        if (active) 1f else 0f,
        tween(250, 0, EaseOutBack)
    )
    val animatedColor = animateColorAsState(
        targetValue = if (active) {
            MaterialTheme.colorScheme.surfaceContainer
        } else {
            MaterialTheme.colorScheme.surfaceContainerLow
        },
        animationSpec = tween(250, 0, EaseOutBack)
    )
    val animatedNonZero = animateFloatAsState(
        if (active) 1f else 0f,
        tween(250, 0, EaseOut)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick,
            shape = CircleShape,
            modifier = Modifier.size(40.dp + 40.dp * onActive),
            elevation = ButtonDefaults.buttonElevation(3.dp * onActive),
            colors = ButtonDefaults.buttonColors(
                containerColor = animatedColor.value
            ),
            border = if (active) BorderStroke(1.dp, verticalGradientBrush()) else null,
            contentPadding = PaddingValues(5.dp * animatedNonZero.value)
        ) {
            Image(
                painterResource(
                    if (active) R.drawable.robo
                    else if (isSystemInDarkTheme()) R.drawable.robo_material_dark
                    else R.drawable.robo_material),
                null,
            )
        }
        Spacer(Modifier.height(20.dp * onActive))
        Text(
            if (active) "Need Help?" else "Ask Ai",
            fontSize = (12 + 2 * onActive).sp,
            fontWeight = if (active) FontWeight.Normal else FontWeight.Light,
            modifier = Modifier.padding(bottom = 10.dp),
        )
    }
}

@Preview
@Composable
fun SpecialButtonPreview() {
    Box(modifier = Modifier
        .size(150.dp)
        .background(Color.hsl(160f, 0.4f, 0.9f)),
        contentAlignment = Alignment.Center
    ){
        SpecialButton({}, true)
    }
}
