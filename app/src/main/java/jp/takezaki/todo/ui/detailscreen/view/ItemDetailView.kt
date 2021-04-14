package jp.takezaki.todo.ui.detailscreen.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun ItemDetailView(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val list: List<TodoItem>? by listViewModel.list.observeAsState()

    // TODO FIX
    val item: TodoItem = list!!.find { it shouldBeUpdatedBy item }!!

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topMenu, contents, bottomBar) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .constrainAs(topMenu) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            DetailTopMenu(item)
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .constrainAs(contents) {
                top.linkTo(topMenu.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomBar.top)
            }
        ) {
            DetailContents(item)
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .constrainAs(bottomBar) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            DetailBottomBar(item)
        }
    }

    BackHandler {
        screenViewModel.setScreen(Screen.ListScreen)
    }
}

@Composable
private fun DetailContents(item: TodoItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        ItemDetailNameView(item)
        DetailTextView(item)
        DetailDueDateView(item)
    }
}
