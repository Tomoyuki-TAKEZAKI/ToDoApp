package jp.takezaki.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

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
        onClick = { model.addNewItem("") },
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
    model: ScreenViewModel = viewModel(),
) {
    Text(
        text = item.name + item.creationDateTime, // for debug
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                model.setScreen(Screen.DetailScreen(item))
            },
        fontSize = 20.sp,
    )
}
