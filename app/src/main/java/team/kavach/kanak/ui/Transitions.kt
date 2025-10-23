package team.kavach.kanak.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.HoverInteraction


val commonEasing : Easing = EaseOutCubic;
private const val fadingTime = 250;
private const val slidingTime = 300;

fun slideFadeIn(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(slidingTime),
        initialOffsetX = { fullWidth -> fullWidth/5 }
    ) + fadeIn(
        animationSpec = tween(fadingTime, easing = commonEasing)
    )
}

fun slideFadeOut(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(slidingTime, easing = commonEasing),
        targetOffsetX = { fullWidth -> -fullWidth/5 }
    ) + fadeOut(
        animationSpec = tween(fadingTime/2)
    )
}

fun popSlideFadeIn(): EnterTransition {
    return slideInHorizontally (
        animationSpec = tween(slidingTime,  easing = commonEasing),
        initialOffsetX = { fullWidth -> -fullWidth/5 }
    ) + fadeIn(animationSpec = tween(fadingTime))
}

fun popSlideFadeOut() : ExitTransition {
    return slideOutHorizontally (
        animationSpec = tween(slidingTime, easing = commonEasing),
        targetOffsetX = { fullWidth -> fullWidth/5 },
    ) + fadeOut(animationSpec = tween(fadingTime/2))
}


fun slideFadeInVertically() : EnterTransition {
    return scaleIn (
        animationSpec = tween(slidingTime, easing = commonEasing),
        initialScale = 0.85f
    ) + fadeIn(animationSpec = tween(fadingTime, 100)
    )
}

fun slideFadeOutVertically() : ExitTransition {
    return scaleOut (
        animationSpec = tween(slidingTime, easing = commonEasing),
        targetScale = 1.15f
    ) + fadeOut(
        animationSpec = tween(fadingTime/2)
    )
}

fun popSlideFadeInVertically() : EnterTransition {
    return scaleIn (
        animationSpec = tween(slidingTime, easing = commonEasing),
        initialScale = 1.15f
    ) + fadeIn(
        animationSpec = tween(fadingTime)
    )
}

fun popSlideFadeOutVertically() : ExitTransition {
    return scaleOut(
        animationSpec = tween(slidingTime, easing = commonEasing),
        targetScale = 0.85f
    ) + fadeOut(
        tween (fadingTime/2)
    )
}