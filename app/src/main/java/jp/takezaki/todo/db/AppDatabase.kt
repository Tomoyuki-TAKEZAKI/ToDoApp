package jp.takezaki.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jp.takezaki.todo.DateTimeConverter
import jp.takezaki.todo.TodoItem

@Database(entities = [TodoItem::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}
