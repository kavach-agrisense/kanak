package team.kavach.kanak

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.kavach.kanak.Scanner.ScannerCard

@Composable
fun AlertScreen(scrollState: ScrollState = rememberScrollState(), modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize().verticalScroll(scrollState)
    ){
        Text("hi")
        ScannerCard()
        ScannerCard()
        ScannerCard()
        ScannerCard()
    }
}