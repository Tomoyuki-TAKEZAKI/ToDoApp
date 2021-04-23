package jp.takezaki.todo.db

import androidx.room.*
import jp.takezaki.todo.TodoItem

@Dao
interface TodoItemDao {
    @Insert
    fun insert(item: TodoItem)

    @Update
    fun update(item: TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Query("SELECT * FROM todo_item")
    fun getAll(): List<TodoItem>
}
