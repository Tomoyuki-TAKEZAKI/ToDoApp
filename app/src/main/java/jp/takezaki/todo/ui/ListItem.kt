package jp.takezaki.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(name: String, isDone: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemState(isDone = isDone)
        ItemText(name = name)
    }
}

@Composable
private fun ItemState(isDone: Boolean) {
    Checkbox(
        checked = isDone,
        onCheckedChange = null,
        modifier = Modifier.clickable(
            onClick = {
                // toggle and save state
            }
        )
    )
}

@Composable
private fun ItemText(name: String) {
    // TODO use TextField
    Text(
        text = name,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}
