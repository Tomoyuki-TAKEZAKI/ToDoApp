package jp.takezaki.todo.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.TodoItem
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun DetailView(
    item: TodoItem,
    listViewModel: ListViewModel = viewModel(),
    screenViewModel: ScreenViewModel = viewModel(),
) {
    val list by listViewModel.list.observeAsState()

    // TODO FIX
    val currentItem = list!!.find { it.hashCode() == item.hashCode() }!!
    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Row {
            BackButton()
            DeleteButton(currentItem)
        }

        Spacer(modifier = Modifier.padding(10.dp))
        ItemDetailText(currentItem)
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
            contentDescription = null
        )
    }
}

@Composable
private fun ItemDetailText(
    item: TodoItem,
    model: ListViewModel = viewModel(),
) {
    Column {
        TextField(
            value = item.name,
            onValueChange = {
                model.modifyItemName(item, it)
            },
            modifier = Modifier.padding(5.dp),
        )
        Text(
            text = "creation date and time: ${item.creationDateTime}",
            fontSize = 20.sp,
        )
    }
}
