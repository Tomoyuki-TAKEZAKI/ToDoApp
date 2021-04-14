package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import java.time.LocalDateTime

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val itemList: List<TodoItem>? by model.list.observeAsState()

    val sectionList: List<Pair<String, (TodoItem) -> Boolean>> = listOf(
        Pair(
            stringResource(id = R.string.listview_header_after_due_date),
            { !it.isDone && it.dueDateTime?.isAfter(LocalDateTime.now()) == true },
        ),
        Pair(
            stringResource(id = R.string.listview_header_before_due_date),
            { !it.isDone && it.dueDateTime?.isBefore(LocalDateTime.now()) == true },
        ),
        Pair(
            stringResource(id = R.string.listview_header_no_due_date),
            { !it.isDone && it.dueDateTime == null },
        ),
        Pair(
            stringResource(id = R.string.listview_header_completed),
            { it.isDone },
        )
    )

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        sectionList.map {
            ListWithHeader(
                list = itemList!!,
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
private fun NewItemDialog(
    showDialog: MutableState<Boolean>,
    model: ListViewModel = viewModel(),
) {
    val itemName = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
        title = {
            Text(text = stringResource(id = R.string.new_item_dialog_title))
        },
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
                Text(stringResource(id = R.string.new_item_dialog_ok))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                }) {
                Text(stringResource(id = R.string.new_item_dialog_cancel))
            }
        }
    )
}
