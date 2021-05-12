package jp.takezaki.todo

import android.app.Application
import jp.takezaki.todo.db.TodoItemDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    val applicationScope: CoroutineScope = CoroutineScope(SupervisorJob())

    val database: TodoItemDataBase by lazy {
        TodoItemDataBase.getDatabase(this, applicationScope)
    }
    val repository: AppRepository by lazy {
        AppRepository(database.todoItemDao())
    }
}