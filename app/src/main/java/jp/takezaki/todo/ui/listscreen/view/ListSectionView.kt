package jp.takezaki.todo.ui.listscreen.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.takezaki.todo.TodoItem


@Composable
fun ListSectionView(
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
