package jp.takezaki.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import jp.takezaki.todo.ui.TodoApp
import jp.takezaki.todo.viewmodel.ListViewModel

class MainActivity : ComponentActivity() {

    private val model: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoApp(model)
        }

    }

}
