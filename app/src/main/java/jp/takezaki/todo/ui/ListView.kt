package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val list by model.list.observeAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        list?.map {
            ListItemView(it)
        }
        AddButton()
    }
}

@Composable
private fun AddButton(model: ListViewModel = viewModel()) {
    Button(
        onClick = { model.addItem("") },
        modifier = Modifier.padding(10.dp),
        content = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
        }
    )
}

@Composable
private fun ListItemView(item: TodoItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCheckboxView(item)
        ItemTextView(item)
        DeleteButton(item)
    }
}

@Composable
private fun ItemCheckboxView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Checkbox(
        checked = item.isDone,
        onCheckedChange = {
            model.updateItemCheckbox(item, it)
        },
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
private fun ItemTextView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    TextField(
        value = item.name,
        onValueChange = {
            model.modifyItemName(item, it)
        },
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
private fun DeleteButton(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Button(
        onClick = {
            model.removeItem(item)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier.padding(5.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null
        )
    }
}
