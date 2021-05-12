package jp.takezaki.todo.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.ui.detailscreen.view.ItemDetailView
import jp.takezaki.todo.ui.listscreen.view.ToDoListView
import jp.takezaki.todo.ui.theme.ToDoTheme
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun TodoApp(
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val screen by screenViewModel.screen.observeAsState()

    ToDoTheme {
        Surface {
            when (val s = screen) {
                is Screen.ListScreen -> ToDoListView(listViewModel)
                is Screen.DetailScreen -> ItemDetailView(s.item)
            }
        }
    }
}
