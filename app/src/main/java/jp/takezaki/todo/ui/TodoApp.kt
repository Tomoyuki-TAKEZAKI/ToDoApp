package jp.takezaki.todo.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import jp.takezaki.todo.ui.theme.ToDoTheme
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun TodoApp() {
    ToDoTheme {
        Surface(color = MaterialTheme.colors.background) {
            ToDoListView()
        }
    }
}
