package jp.takezaki.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
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
import java.time.LocalDateTime

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val list by model.list.observeAsState()

    val sectionList: List<Pair<String, (TodoItem) -> Boolean>> = listOf(
        Pair(
            "After due date",
            { it.dueDateTime?.isAfter(LocalDateTime.now()) == true },
        ),
        Pair(
            "Before due date",
            { it.dueDateTime?.isBefore(LocalDateTime.now()) == true },
        ),
        Pair(
            "No due date",
            { it.dueDateTime == null },
        ),
    )

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        sectionList.map {
            ListWithHeader(
                list = list!!,
                headerText = it.first,
                predicate = it.second,
            )
        }

        NewItemButton()
    }
}

@Composable
private fun ListWithHeader(
    list: List<TodoItem>,
    headerText: String,
    predicate: (TodoItem) -> Boolean,
) {
    val filteredList = list.filter(predicate)
    if (filteredList.isEmpty()) return

    Text(
        text = headerText,
        fontSize = 20.sp,
        modifier = Modifier.padding(5.dp),
    )
    filteredList.map {
        ListItemView(it)
    }
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
        text = item.name,
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                model.setScreen(Screen.DetailScreen(item))
            },
        fontSize = 20.sp,
    )
}

@Composable
private fun NewItemButton() {
    val showDialog: MutableState<Boolean> = remember { mutableStateOf(false) }

    Button(
        onClick = {
            showDialog.value = true
        },
        modifier = Modifier.padding(10.dp),
        content = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
        }
    )

    if (showDialog.value) {
        NewItemDialog(showDialog)
    }
}

@Composable
fun NewItemDialog(
    showDialog: MutableState<Boolean>,
    model: ListViewModel = viewModel(),
) {
    val itemName = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
        title = { Text(text = "New item") },
        text = {
            TextField(
                value = itemName.value,
                onValueChange = {
                    itemName.value = it
                }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    showDialog.value = false
                    if (itemName.value.isEmpty()) return@Button
                    model.addNewItem(itemName.value)
                }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                }) {
                Text("Cancel")
            }
        }
    )
}
