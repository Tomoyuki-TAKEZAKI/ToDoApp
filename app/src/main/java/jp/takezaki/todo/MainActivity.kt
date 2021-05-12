package jp.takezaki.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import jp.takezaki.todo.ui.TodoApp
import jp.takezaki.todo.viewmodel.ListViewModel
import jp.takezaki.todo.viewmodel.ListViewModelFactory

class MainActivity : ComponentActivity() {

    private val listViewModel : ListViewModel by viewModels {
        ListViewModelFactory((application as MainApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoApp(listViewModel)
        }
    }
}
