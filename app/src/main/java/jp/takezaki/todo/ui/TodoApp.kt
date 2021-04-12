package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.takezaki.todo.ui.theme.ToDoTheme
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun TodoApp(model: ListViewModel) {
    ToDoTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ToDoListView(model)
            }
        }
    }
}
