package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.takezaki.todo.ui.theme.ToDoTheme

@Composable
fun TodoApp() {
    ToDoTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ToDoList()
            }
        }
    }
}

@Composable
fun ToDoList() {
    Column {
        getSampleList().map { // for testing
            ListItem(name = it.first, isDone = it.second)
        }
    }
}

// for testing
private fun getSampleList(): List<Pair<String, Boolean>> =
    (1..100).map {
        Pair("test $it ${"test".repeat(5)}", it % 2 == 0)
    }
