package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.ui.theme.ToDoTheme

@Composable
fun TodoApp(list: List<TodoItem>) {
    ToDoTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ToDoListView(list)
            }
        }
    }
}

@Composable
fun ToDoListView(list: List<TodoItem>) {
    Column {
        list.map {
            ListItemView(item = it)
        }
    }
}
