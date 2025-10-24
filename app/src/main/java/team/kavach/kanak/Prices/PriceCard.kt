package team.kavach.kanak.Prices

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toursafe.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.inverseVerticalGradientBrush
import team.kavach.kanak.ui.theme.verticalGradientBrush
import javax.crypto.KeyGenerator
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun PriceCard() {

    val inQuintal = remember { mutableStateOf(true) }

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
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Prices",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMSansFontFamily,
                )
                UnitKnob(onClick = {inQuintal.value = it })
            }

            Spacer(modifier = Modifier.height(5.dp))
            PriceItem("Narela", "14 March '24", 2485, inQuintal)
            Separator()
            PriceItem("Najafgarh", "4 September '25", 2915, inQuintal)
        }
    }
}

@Composable
fun Separator() {
    Box(Modifier
        .height(8.dp)
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
        .clip(
            RoundedCornerShape(5.dp))
        .background(brush = Brush.verticalGradient(listOf(
            MaterialTheme.colorScheme.surfaceDim,
            MaterialTheme.colorScheme.surface
        )))

        .border(1.dp, inverseVerticalGradientBrush(), RoundedCornerShape(5.dp))
    )
}

@Composable
fun PriceItem(location : String, date : String, price : Int, showQuintal : MutableState<Boolean>) {

    val price = animateIntAsState(if (showQuintal.value) {
        price
    } else {
        (price / 100f).roundToInt()
    },
        tween(150, 0, EaseOut)
    )
    Row (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Column {
            Text(
                location,
                fontSize = 24.sp,
                fontFamily = DMSansFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Text(
                date,
                fontWeight = FontWeight.Light,
                fontFamily = DMSansFontFamily,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 12.sp
            )
        }
        Text(
            "₹ ${price.value}",
            fontFamily = DMSansFontFamily,
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun UnitKnob (onClick : (inQuintal : Boolean) -> Unit = {}) {

    var quintalActive by remember{ mutableStateOf(true) }

    val animatedOffset = animateDpAsState(
        targetValue = if (quintalActive) {
            23.dp
        } else {
            (-45).dp
        },
        tween(200, 0, EaseOutBack)
    )



    val animatedWidth = animateDpAsState(
        targetValue = if (quintalActive) {
            85.dp
        } else {
            45.dp
        },
        tween(200, 0, EaseOutBack)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    quintalActive = !quintalActive;
                    onClick(quintalActive)
                          },
                indication = null
            )
    ) {
        Button(
            onClick = {
                quintalActive = !quintalActive;
                onClick(quintalActive)
            },
            modifier = Modifier.offset(animatedOffset.value).width(animatedWidth.value),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            border = BorderStroke(1.dp, verticalGradientBrush()),
            elevation = ButtonDefaults.elevatedButtonElevation(3.dp)
        ) {}
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                "Kg",
                fontSize = 18.sp,
                fontFamily = DMSansFontFamily,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.width(25.dp))
            Text(
                "Quintal",
                fontSize = 18.sp,
                fontFamily = DMSansFontFamily,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview("Unit Knob")
@Composable
fun UnitKnobPreview() {
    Box(Modifier.size(150.dp, 100.dp).background(MaterialTheme.colorScheme.surface)) {
        UnitKnob()
    }

}

@Preview ("Price Card")
@Composable
fun PriceCardPreview() {
    Box(
        Modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        PriceCard()
    }
}