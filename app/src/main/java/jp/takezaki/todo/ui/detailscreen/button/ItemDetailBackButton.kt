package jp.takezaki.todo.ui.detailscreen.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.takezaki.todo.Screen
import jp.takezaki.todo.viewmodel.ScreenViewModel

@Composable
fun ItemDetailBackButton(model: ScreenViewModel = viewModel()) {
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
