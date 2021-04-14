package jp.takezaki.todo.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.R
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
    val currentItem: TodoItem = list!!.find { it shouldBeUpdatedBy item }!!

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Row {
            BackButton()
            DeleteButton(currentItem)
            CheckBoxButton(currentItem)
        }

        Spacer(modifier = Modifier.padding(10.dp))
        ItemNameView(currentItem)
        ItemDetailTextView(currentItem)
        ItemDueDateView(currentItem)
    }

    BackHandler {
        screenViewModel.setScreen(Screen.ListScreen)
    }
}

@Composable
private fun BackButton(model: ScreenViewModel = viewModel()) {
    Button(
        onClick = {
            model.setScreen(Screen.ListScreen)
        },
        modifier = Modifier.padding(5.dp),
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    }
}

@Composable
private fun DeleteButton(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    Button(
        onClick = {
            listViewModel.removeItem(item)
            screenViewModel.setScreen(Screen.ListScreen)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        modifier = Modifier.padding(5.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
        )
    }
}

@Composable
private fun CheckBoxButton(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val msg: String = stringResource(
        if (item.isDone) {
            R.string.mark_as_undone
        } else {
            R.string.mark_as_done
        }
    )

    Button(
        onClick = {
            listViewModel.updateItemCheckbox(item, !item.isDone)
            if (!item.isDone) screenViewModel.setScreen(Screen.ListScreen)
        },
        modifier = Modifier.padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
        )
        Text(
            text = msg,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    }
}

@Composable
private fun ItemNameView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Column {
        Text(
            text = stringResource(R.string.item_detail_name),
            fontSize = 20.sp,
        )
        TextField(
            value = item.name,
            onValueChange = {
                model.updateItemName(item, it)
            },
            modifier = Modifier.padding(5.dp),
        )
    }
}

@Composable
private fun ItemDetailTextView(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Column {
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

@Composable
private fun ItemDueDateView(
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
    }
}
