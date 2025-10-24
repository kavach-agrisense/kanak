package team.kavach.kanak

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.kavach.kanak.Farm.FieldCard
import team.kavach.kanak.Navigation.Screen

@Composable
fun FarmScreen(modifier : Modifier = Modifier, scrollState : ScrollState = rememberScrollState()) {
    Column (
        modifier.fillMaxSize().verticalScroll(scrollState)
    ){
        FieldCard()
    }
}


@Preview("Farm Screen")
@Composable
fun ScreenPreview() {
    FarmScreen()
}