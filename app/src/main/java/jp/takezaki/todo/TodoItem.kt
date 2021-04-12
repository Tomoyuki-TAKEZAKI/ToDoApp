package jp.takezaki.todo

import java.time.LocalDateTime

class TodoItem private constructor(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val dateTime: LocalDateTime,
) {

    companion object Factory {
        fun getNewItem(id: Int, name: String): TodoItem =
            TodoItem(id, name, false, LocalDateTime.now())

        fun getUpdatedItem(item: TodoItem, newName: String): TodoItem =
            TodoItem(item.id, newName, item.isDone, item.dateTime)

        fun getUpdatedItem(item: TodoItem, isDone: Boolean): TodoItem =
            TodoItem(item.id, item.name, isDone, item.dateTime)

    }

    override fun toString(): String = "TodoItem(id: $id, name: $name, isDone: $isDone, dateTime: $dateTime)"

}