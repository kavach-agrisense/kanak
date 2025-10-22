package team.kavach.kanak

import android.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Chat
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toursafe.ui.theme.DMSansFontFamily

@Composable
fun ChatButton(expanded : Boolean = true) {
    ExtendedFloatingActionButton(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.surfaceTint,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(10.dp)
    ) {
        Icon(Icons.AutoMirrored.Rounded.Chat, null, Modifier.size(30.dp))
        Spacer(Modifier.width(10.dp))
        if (expanded) {
            Text("Ask AI", fontSize = 22.sp, fontFamily = DMSansFontFamily)
        }
    }
}

@Preview
@Composable
fun ChatButtonPreview() {
    ChatButton()
}