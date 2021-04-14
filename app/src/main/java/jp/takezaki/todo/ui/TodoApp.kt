package jp.takezaki.todo.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.ui.theme.ToDoTheme
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun TodoApp(model: ScreenViewModel = viewModel()) {

    val screen by model.screen.observeAsState()

    ToDoTheme {
        Surface {
            when (val s = screen) {
                is Screen.ListScreen -> ToDoListView()
                is Screen.DetailScreen -> ItemDetailView(s.item)
            }
        }
    }
}
