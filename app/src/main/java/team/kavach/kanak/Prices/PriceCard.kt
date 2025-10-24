package team.kavach.kanak.Prices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toursafe.ui.theme.DMSansFontFamily

@Composable
fun PriceCard() {
    Card (
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Column (Modifier.padding(15.dp, 10.dp)) {
            PriceItem()
        }
    }
}

@Composable
fun PriceItem() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(
                "Narela",
                fontSize = 20.sp,
                fontFamily = DMSansFontFamily,
                fontWeight = FontWeight.Medium
            )
            Text(
                "14 March '24",
                fontWeight = FontWeight.Light,
                fontFamily = DMSansFontFamily,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 12.sp
            )
        }
        Text(
            "₹ 2485",
            fontFamily = DMSansFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview ("Price Card")
@Composable
fun PriceCardPreview() {
    PriceCard()
}