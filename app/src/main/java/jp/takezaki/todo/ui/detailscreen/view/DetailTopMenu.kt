package jp.takezaki.todo.ui.detailscreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.ui.detailscreen.button.ItemDetailBackButton
import jp.takezaki.todo.ui.detailscreen.button.ItemDetailDeleteButton

@Composable
fun DetailTopMenu(item: TodoItem) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (back, delete) = createRefs()
        Box(modifier = Modifier.constrainAs(back) {
            start.linkTo(parent.start)
        }) {
            ItemDetailBackButton()
        }

        Box(modifier = Modifier.constrainAs(delete) {
            end.linkTo(parent.end)
        }) {
            ItemDetailDeleteButton(item)
        }
    }
}
