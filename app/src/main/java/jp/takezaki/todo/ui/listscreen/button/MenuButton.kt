package jp.takezaki.todo.ui.listscreen.button

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun MenuButton() {
    Button(onClick = {
        // TODO show menu
    }) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
    }
}