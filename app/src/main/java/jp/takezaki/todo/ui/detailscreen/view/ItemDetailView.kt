package jp.takezaki.todo.ui.detailscreen.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.ui.detailscreen.button.ItemDetailBackButton
import jp.takezaki.todo.ui.detailscreen.button.ItemDetailCheckBoxButton
import jp.takezaki.todo.ui.detailscreen.button.ItemDetailDeleteButton
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun ItemDetailView(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val list: List<TodoItem>? by listViewModel.list.observeAsState()

    // TODO FIX
    val currentItem: TodoItem = list!!.find { it shouldBeUpdatedBy item }!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row {
            ItemDetailBackButton()
            ItemDetailDeleteButton(currentItem)
            ItemDetailCheckBoxButton(currentItem)
        }

        ItemDetailNameView(currentItem)
        DetailTextView(currentItem)
        DetailDueDateView(currentItem)
    }

    BackHandler {
        screenViewModel.setScreen(Screen.ListScreen)
    }
}
