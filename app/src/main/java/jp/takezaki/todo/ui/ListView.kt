package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val list = remember { mutableStateOf(model.list.value) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val addButton = createRef()
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            list.value!!.map {
                ListItemView(it, list)
            }
        }
        FloatingActionButton(
            onClick = {
                model.addItem("")
                list.value = model.list.value
            },
            modifier = Modifier
                .constrainAs(addButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(20.dp),
            content = {}
        )
    }
}

@Composable
fun ListItemView(
    item: TodoItem,
    list: MutableState<List<TodoItem>?>,
    model: ListViewModel = viewModel(),
) {
    val itemState = remember { mutableStateOf(item) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCheckboxView(itemState)
        ItemTextView(itemState)
        Button(
            onClick = {
                model.removeItem(item)
                list.value = model.list.value
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier.padding(5.dp),
        ) {
            Text(text = "x")
        }
    }
}

@Composable
private fun ItemCheckboxView(
    itemState: MutableState<TodoItem>,
    model: ListViewModel = viewModel(),
) {
    Checkbox(
        checked = itemState.value.isDone,
        onCheckedChange = {
            itemState.value = model.updateItemCheckbox(itemState.value, it)
        },
        modifier = Modifier.padding(5.dp),
    )
}

@Composable
private fun ItemTextView(
    itemState: MutableState<TodoItem>,
    model: ListViewModel = viewModel(),
) {
    TextField(
        value = itemState.value.name,
        onValueChange = {
            itemState.value = model.modifyItemName(itemState.value, it)
        },
        modifier = Modifier.padding(5.dp),
    )
}
