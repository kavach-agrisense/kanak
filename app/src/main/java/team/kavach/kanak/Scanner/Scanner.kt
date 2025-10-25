package team.kavach.kanak.Scanner

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.kavach.kanak.R
import team.kavach.kanak.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.verticalGradientBrush

@Composable
fun ScannerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, verticalGradientBrush()),
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                " Crop Health",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(5.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column (Modifier.width(100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(R.drawable.click_photo),
                        null,
                        Modifier.size(60.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Click a photo",
                        modifier = Modifier.fillMaxWidth().height(35.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }
                Image(
                    painterResource(R.drawable.arrow_right),
                    null,
                    Modifier.size(30.dp)
                )
                Column(Modifier.width(100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(R.drawable.report),
                        null,
                        Modifier.size(60.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Get diagnosis results",
                        modifier = Modifier.fillMaxWidth().height(35.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = TextUnit(14f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }

            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding()
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                shape = RoundedCornerShape(22.dp),
                border = BorderStroke(1.dp, verticalGradientBrush()),
                elevation = ButtonDefaults.buttonElevation(
                    4.dp
                )
            ) {
                Text(
                    "Scan",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp),
                    fontFamily = DMSansFontFamily,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScannerCard() {
    ScannerCard()
}