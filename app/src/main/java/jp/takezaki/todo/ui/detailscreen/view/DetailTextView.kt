package jp.takezaki.todo.ui.detailscreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel


@Composable
fun DetailTextView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = stringResource(R.string.item_detail_text),
            fontSize = 20.sp,
        )
        TextField(
            value = item.detailText,
            onValueChange = {
                model.updateItemDetailText(item, it)
            },
            modifier = Modifier.padding(5.dp),
        )
    }
}
