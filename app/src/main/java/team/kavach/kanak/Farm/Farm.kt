package team.kavach.kanak.Farm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import team.kavach.kanak.ui.theme.DMSansFontFamily
import team.kavach.kanak.ui.theme.verticalGradientBrush

val exampleFieldPoints = listOf<Pair<Float,Float>>(
    Pair(4f, 20f),
    Pair(20.7f, 25f),
    Pair(27.36f, 8.6f),
    Pair(15.3f, 2.6f),
    Pair(4.6f, 5.7f)
)

@Composable
fun FieldCard(fieldName : String = "North Field") {

    Card (
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fieldName,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = DMSansFontFamily,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(20.dp))
            Box(contentAlignment = Alignment.Center) {
                RoundedPolygonFromPoints(exampleFieldPoints)
            }
            Spacer(Modifier.height(20.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    border = BorderStroke(1.dp, verticalGradientBrush()),
                    shape = RoundedCornerShape(15.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(2.dp)
                ) {
                    Icon(Icons.Rounded.Map, null)
                    Text(" See on map")
                }
            }
        }

    }
}

@Composable
fun RoundedPolygonFromPoints(points: List<Pair<Float, Float>>) {
    val color = MaterialTheme.colorScheme.primary;
    var minX = Float.MAX_VALUE;
    var maxX = Float.MIN_VALUE;
    var minY = Float.MAX_VALUE;
    var maxY = Float.MIN_VALUE;

    for ((x : Float, y : Float) in points) {
        if (x < minX) {
            minX = x;
        } else if (maxX < x) {
            maxX = x;
        }
        if (y < minY) {
            minY = y
        } else if (y > maxY) {
            maxY = y
        }
    }

    val maxSide = if ((maxX - minX) > (maxY - minY)) maxX - minX else maxY - minY;
    val borderBrush = verticalGradientBrush();

    Box(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .drawWithCache {
                val width = size.width
                val height = size.height

                val vertices = FloatArray(points.size * 2)
                for ((i, p) in points.withIndex()) {
                    vertices[i * 2] = (p.first - minX) / maxSide * width
                    vertices[i * 2 + 1] = (p.second - minY) / maxSide * height
                }

                val polygon = RoundedPolygon(
                    vertices = vertices,
                    rounding = CornerRounding(20f, 0.5f)
                )

                val dashLength = 15f
                val gapLength = 10f
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), 0f)

                val path = polygon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(
                        path,
                        color
                    )

                    drawPath(
                        path,
                        brush =  borderBrush,
                        style = Stroke(
                            width = 2f
                        )
                    )

                    drawCircle(
                        color = Color.hsl(20f, 0.5f, 0.8f, 0.5f),
                        radius = size.width * 0.2f,
                        center = Offset(width * 0.75f, size.height * 0.75f)
                    )
                    drawCircle(
                        color = Color.hsl(20f, 0.5f, 0.8f, 0.5f),
                        radius = size.width * 0.1f,
                        center = Offset(width * 0.75f, size.height * 0.75f),
                        blendMode = BlendMode.Overlay
                    )
                    drawCircle(
                        color = Color.hsl(20f, 0.5f, 0.3f, 1f),
                        radius = size.width * 0.1f,
                        center = Offset(width * 0.75f, size.height * 0.75f),
                        style = Stroke(
                            2f,
                            pathEffect = pathEffect
                        )
                    )
                }
            }.size(200.dp)
    ) {

    }
}

@Preview("Field Card Preview")
@Composable
fun Field () {
    FieldCard()
}

