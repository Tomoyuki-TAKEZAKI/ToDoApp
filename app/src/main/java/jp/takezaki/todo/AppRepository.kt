package jp.takezaki.todo

import androidx.annotation.WorkerThread
import jp.takezaki.todo.db.TodoItemDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val itemDao: TodoItemDao) {

    val allItems: Flow<List<TodoItem>> = itemDao.getAll()

    @WorkerThread
    suspend fun insert(item: TodoItem) {
        itemDao.insert(item)
    }

    @WorkerThread
    suspend fun update(item: TodoItem) {
        itemDao.update(item)
    }

    @WorkerThread
    suspend fun delete(item: TodoItem) {
        itemDao.delete(item)
    }

}