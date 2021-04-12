package jp.takezaki.todo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel

@Composable
fun ToDoListView(model: ListViewModel) {
    Column {
        model.list.value?.map {
            ListItemView(it, model)
        }
    }
}


@Composable
fun ListItemView(item: TodoItem, model: ListViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemStateView(item, model)
        ItemTextView(item, model)
    }
}

@Composable
private fun ItemStateView(
    item: TodoItem,
    model: ListViewModel,
) {
    Checkbox(
        checked = item.isDone,
        onCheckedChange = null,
        modifier = Modifier.clickable {
            model.toggleItem(item)
        }
    )
}

@Composable
private fun ItemTextView(
    item: TodoItem,
    model: ListViewModel,
) {
    // TODO use TextField
    Text(
        text = item.name,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}
