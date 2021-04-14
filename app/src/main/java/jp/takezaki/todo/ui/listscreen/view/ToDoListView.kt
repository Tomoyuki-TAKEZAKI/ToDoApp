package jp.takezaki.todo.ui.listscreen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
        val (listSectionView, newItemButton) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .constrainAs(listSectionView) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(newItemButton.top)
                }
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
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

        Box(
            modifier = Modifier
                .fillMaxHeight(0.1f)
                .constrainAs(newItemButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            BottomBar()
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
