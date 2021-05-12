package jp.takezaki.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import jp.takezaki.todo.DateTimeConverter
import jp.takezaki.todo.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Database(entities = [TodoItem::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class TodoItemDataBase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao

    companion object {

        @Volatile
        private var INSTANCE: TodoItemDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): TodoItemDataBase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemDataBase::class.java,
                    "todoitem_database"
                ).addCallback(TodoDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
    }

    private class TodoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.todoItemDao())
                }
            }
        }

        fun populateDatabase(dao: TodoItemDao) {
            dao.deleteAll()
        }

    }
}
