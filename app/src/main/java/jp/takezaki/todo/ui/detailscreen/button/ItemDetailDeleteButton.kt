package jp.takezaki.todo.ui.detailscreen.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun ItemDetailDeleteButton(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    Button(
        onClick = {
            listViewModel.deleteItem(item)
            screenViewModel.setScreen(Screen.ListScreen)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier.padding(5.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
        )
    }
}
