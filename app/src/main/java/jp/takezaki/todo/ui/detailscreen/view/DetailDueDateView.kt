package jp.takezaki.todo.ui.detailscreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel


@Composable
fun DetailDueDateView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    val dueDateString = if (item.dueDateTime != null) {
        item.dueDateTime.toString()
    } else {
        stringResource(R.string.item_detail_no_due_date)
    }

    Column {
        Text(
            text = stringResource(R.string.item_detail_due_date),
            fontSize = 20.sp,
        )
        Text(
            text = dueDateString,
            fontSize = 20.sp,
        )
        // TODO add date picker
    }
}
