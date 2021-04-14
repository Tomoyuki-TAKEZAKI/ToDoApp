package jp.takezaki.todo.ui.detailscreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun DetailBottomBar(item: TodoItem) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val editListButton = createRef()

        Box(
            modifier = Modifier
                .constrainAs(editListButton) {
                    end.linkTo(parent.end)
                }
                .padding(10.dp)
        ) {
            ItemDetailCheckBoxSwitchButton(item)
        }
    }
}

@Composable
private fun ItemDetailCheckBoxSwitchButton(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val msgId: Int = if (item.isDone) {
        R.string.item_detail_mark_uncompleted
    } else {
        R.string.item_detail_mark_completed
    }
    Button(onClick = {
        listViewModel.updateItemCheckbox(item, !item.isDone)
        if (!item.isDone) screenViewModel.setScreen(Screen.ListScreen)
    }) {
        Text(
            text = stringResource(msgId)
        )
    }

}
