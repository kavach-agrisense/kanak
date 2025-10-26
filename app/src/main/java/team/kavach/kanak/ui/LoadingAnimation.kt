import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.*

// The main Composable function for the dynamic shape.
@Composable
fun MorphingRotatingShape(modifier: Modifier = Modifier) {
    // 1. Setup Animation Drivers
    val infiniteTransition = rememberInfiniteTransition(label = "ShapeAnimations")

    // Continuous 360-degree rotation animation
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Rotation"
    )

    // Continuous morphing progress: cycles from 0 to 5 (for 5 shapes: 3, 4, 5, 6, 7 sides)
    val sidesList = remember { listOf(3, 4, 5, 6, 7) }
    val morphProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = sidesList.size.toFloat(),
        animationSpec = infiniteRepeatable(
            // Changed the base duration from 1500ms to 3000ms, making the total cycle 15 seconds.
            animation = tween(3000 * sidesList.size, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "MorphProgress"
    )

    // 2. State Calculation
    val sidesCount = sidesList.size

    // Current shape index (e.g., 0 for 3 sides, 1 for 4 sides)
    val currentShapeIndex = floor(morphProgress).toInt().coerceIn(0, sidesCount - 1)

    // Next shape index (e.g., 1 for 4 sides when morphing from 3)
    val nextShapeIndex = ((currentShapeIndex + 1) % sidesCount)

    // Progress of the transition between the current and next shape (0.0 to 1.0)
    val transitionProgress = morphProgress - floor(morphProgress).toInt()

    // 3. Drawing Canvas
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E2E)) // Dark background
            .padding(16.dp)
    ) {
        val sizePx = min(size.width, size.height)
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = sizePx / 3f
        // The line referencing LocalDensity.current has been removed here because it was causing the
        // Composable context error and was commented as 'Unused' in the original code.

        // --- Core Shape Generation and Interpolation ---
        val path = createInterpolatedPath(
            center = center,
            radius = radius,
            startSides = sidesList[currentShapeIndex],
            endSides = sidesList[nextShapeIndex],
            progress = transitionProgress,
            rotationDegrees = rotation
        )

        val shapeColor = Color(0xFF89CFF0) // Light Blue
        val outlineColor = Color(0xFFCBA6F7) // Pale Purple

        // --- 4. Inner Shadow Effect (Faux using BlendMode) ---

        // A. Draw a dark radial gradient clipped to the shape.
        // This simulates a slight concavity (inner shadow/depth) by darkening the edges.
        drawPath(
            path = path,
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Black.copy(alpha = 0.0f), // Center: fully transparent (reveals main color)
                    Color.Black.copy(alpha = 0.4f)  // Edge: darkens the main color
                ),
                center = center,
                radius = radius,
            ),
            style = Fill,
            blendMode = BlendMode.SrcAtop // Ensures the gradient only draws where the shape already exists (on top of it).
        )

        // B. Draw the main shape color on top with a slightly lighter color
        // to give the center a 'highlighted' look after the shadow layer.
        drawPath(path = path, color = shapeColor.copy(alpha = 0.8f), style = Fill)


        // C. Draw a final, darker outline stroke for definition.
        drawPath(
            path = path,
            color = outlineColor,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}

// Helper function to generate a fixed list of 7 points for any polygon from 3 to 7 sides.
// This ensures smooth morphing because both the starting and ending shapes have an equal number of points (7).
fun createRoundedPolygonPoints(sides: Int, center: Offset, radius: Float): List<Offset> {
    val maxPoints = 7 // Fixed number of points for stable interpolation
    val points = mutableListOf<Offset>()

    // The base angle step is for a fixed 7-point structure
    val baseAngleIncrement = 360f / maxPoints

    for (i in 0 until maxPoints) {
        val baseAngle = baseAngleIncrement * i - 90f // Start pointing up (-90 degrees)

        // Find the nearest actual vertex angle of the N-sided polygon.
        val angleIncrement = 360f / sides
        val nearestVertexAngle = (round((baseAngle + 90f) / angleIncrement) * angleIncrement) - 90f

        // Proximity calculation: how close the 7-point angle is to a real vertex angle.
        val distanceToVertex = abs(baseAngle - nearestVertexAngle)

        // Calculate a factor that makes the radius peak at actual vertices of the 'sides' polygon.
        // We use the cosine function for smooth, periodic radius modulation.
        val radiusFactor = cos(Math.toRadians(distanceToVertex.toDouble() * (360f / angleIncrement) * 0.1)).toFloat()

        // Adjust the radius based on the factor: closer to a real vertex -> larger radius, further -> smaller radius.
        // This is the core of the morphing simulation.
        val modulatedRadius = radius + radius * 0.15f * radiusFactor

        val x = center.x + modulatedRadius * cos(Math.toRadians(baseAngle.toDouble())).toFloat()
        val y = center.y + modulatedRadius * sin(Math.toRadians(baseAngle.toDouble())).toFloat()

        points.add(Offset(x, y))
    }
    return points
}

// Function to perform the point-by-point interpolation and Path construction.
fun createInterpolatedPath(
    center: Offset,
    radius: Float,
    startSides: Int,
    endSides: Int,
    progress: Float,
    rotationDegrees: Float
): Path {
    // 1. Get the 7-point sets for the start and end shapes
    val startPoints = createRoundedPolygonPoints(startSides, center, radius)
    val endPoints = createRoundedPolygonPoints(endSides, center, radius)

    // 2. Interpolate all 7 points
    val interpolatedPoints = (0 until 7).map { i ->
        val start = startPoints[i]
        val end = endPoints[i]

        // Linear interpolation: (1-progress)*start + progress*end
        val x = lerp(start.x, end.x, progress)
        val y = lerp(start.y, end.y, progress)

        // 3. Apply global rotation to the interpolated point
        val dx = x - center.x
        val dy = y - center.y
        val angleRad = Math.toRadians(rotationDegrees.toDouble()).toFloat()

        val rotatedX = center.x + dx * cos(angleRad) - dy * sin(angleRad)
        val rotatedY = center.y + dx * sin(angleRad) + dy * cos(angleRad)

        Offset(rotatedX, rotatedY)
    }

    // 4. Build the Path (using straight lines for simplicity, as true rounded corners
    // on a morphing path require complex Bezier curve calculations).
    val path = Path()
    path.moveTo(interpolatedPoints.first().x, interpolatedPoints.first().y)

    for (i in 1 until interpolatedPoints.size) {
        path.lineTo(interpolatedPoints[i].x, interpolatedPoints[i].y)
    }
    path.close()

    return path
}

// A simple entry point for previewing the Composable
@Composable
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MorphingRotatingShape(modifier = Modifier.size(300.dp))
        }
    }
}


@Preview
@Composable
fun AppPreview() {
    App()
}