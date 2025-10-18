package team.kavach.kanak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import team.kavach.kanak.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme () {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavBar()
        },
        floatingActionButton = {
            ChatButton()
        }
    ) { innerPadding ->
        HomeScreen(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        Modifier.background(Color(0xfffbf1c7)).fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}