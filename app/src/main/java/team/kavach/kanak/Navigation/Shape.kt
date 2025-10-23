package team.kavach.kanak.Navigation

import androidx.compose.foundation.shape.GenericShape

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
            dipCenterX - dipRadius / 2, dipRadius * 0.5f,
        )

        quadraticTo(
            dipCenterX, dipRadius * 4 / 5,
            dipCenterX + dipRadius / 2, dipRadius * 0.5f,
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