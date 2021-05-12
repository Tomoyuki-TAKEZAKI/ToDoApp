package jp.takezaki.todo.ui.listscreen.button

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable

@Composable
fun EditListButton() {
    Button(onClick = {
        // TODO show edit list menu
    }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
}
