package jp.takezaki.todo.ui.listscreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import java.time.LocalDateTime

@Composable
fun ToDoListView(model: ListViewModel = viewModel()) {
    val itemList: List<TodoItem>? by model.list.observeAsState()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(bottomBar = { BottomBar() }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight(0.9f)
            ) {
                sectionList().map {
                    ListSectionView(
                        list = itemList!!,
                        headerText = stringResource(id = it.first),
                        predicate = it.second,
                    )
                }
            }
        }
    }
}

private fun sectionList(): List<Pair<Int, (TodoItem) -> Boolean>> = listOf(
    Pair(
        R.string.listview_header_after_due_date,
        { !it.isDone && it.dueDateTime?.isAfter(LocalDateTime.now()) == true },
    ),
    Pair(
        R.string.listview_header_before_due_date,
        { !it.isDone && it.dueDateTime?.isBefore(LocalDateTime.now()) == true },
    ),
    Pair(
        R.string.listview_header_no_due_date,
        { !it.isDone && it.dueDateTime == null },
    ),
    Pair(
        R.string.listview_header_completed,
        { it.isDone },
    )
)
