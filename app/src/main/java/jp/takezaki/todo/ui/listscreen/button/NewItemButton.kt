package jp.takezaki.todo.ui.listscreen.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun NewItemButton() {
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
                    model.addNewItem(itemName.value)
                }) {
                Text(stringResource(id = R.string.new_item_dialog_save))
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
