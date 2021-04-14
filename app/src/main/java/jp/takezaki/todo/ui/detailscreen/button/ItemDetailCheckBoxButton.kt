package jp.takezaki.todo.ui.detailscreen.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun ItemDetailCheckBoxButton(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val msgId: Int = if (item.isDone) {
        R.string.item_detail_mark_uncompleted
    } else {
        R.string.item_detail_mark_completed
    }

    Button(
        onClick = {
            listViewModel.updateItemCheckbox(item, !item.isDone)
            if (!item.isDone) screenViewModel.setScreen(Screen.ListScreen)
        },
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
        )
        Text(
            text = stringResource(msgId),
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
}
