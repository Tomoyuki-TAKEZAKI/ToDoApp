package jp.takezaki.todo

import androidx.annotation.WorkerThread
import jp.takezaki.todo.db.TodoItemDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val itemDao: TodoItemDao) {

    val allItems: Flow<List<TodoItem>> = itemDao.getAll()

    @WorkerThread
    fun insert(item: TodoItem) {
        itemDao.insert(item)
    }

    @WorkerThread
    fun update(item: TodoItem) {
        itemDao.update(item)
    }

    @WorkerThread
    fun delete(item: TodoItem) {
        itemDao.delete(item)
    }

    @WorkerThread
    fun deleteCompleted() {
        itemDao.deleteCompleted()
    }

}