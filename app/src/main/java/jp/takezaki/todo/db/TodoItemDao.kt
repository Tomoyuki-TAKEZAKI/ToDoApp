package jp.takezaki.todo.db

import androidx.room.*
import jp.takezaki.todo.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {

    @Insert
    fun insert(item: TodoItem)

    @Update
    fun update(item: TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Query("DELETE FROM todo_item")
    fun deleteAll()

    @Query("DELETE FROM todo_item WHERE is_done = 1")
    fun deleteCompleted()

    @Query("SELECT * FROM todo_item ORDER BY creation_datetime DESC")
    fun getAll(): Flow<List<TodoItem>>

}
