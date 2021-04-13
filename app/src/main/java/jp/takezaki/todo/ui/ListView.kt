package jp.takezaki.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val list by model.list.observeAsState()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val addButton = createRef()
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            list?.map {
                ListItemView(it)
            }
        }
        FloatingActionButton(
            onClick = { model.addItem("") },
            modifier = Modifier
                .constrainAs(addButton) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(20.dp),
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                )
            }
        )
    }
}

@Composable
fun ListItemView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCheckboxView(item)
        ItemTextView(item)
        Button(
            onClick = {
                model.removeItem(item)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier.padding(5.dp),
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
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
