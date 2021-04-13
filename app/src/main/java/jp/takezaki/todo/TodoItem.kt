package jp.takezaki.todo

import java.time.LocalDateTime

class TodoItem private constructor(
    val name: String,
    val isDone: Boolean,
    val dateTime: LocalDateTime,
) {

    companion object Factory {
        fun getNewItem(name: String): TodoItem =
            TodoItem(name, false, LocalDateTime.now())

        fun getUpdatedItem(item: TodoItem, newName: String): TodoItem =
            TodoItem(newName, item.isDone, item.dateTime)

        fun getUpdatedItem(item: TodoItem, isDone: Boolean): TodoItem =
            TodoItem(item.name, isDone, item.dateTime)
    }

    override fun toString(): String = "TodoItem(name: $name, isDone: $isDone, dateTime: $dateTime)"

    override fun hashCode(): Int = dateTime.hashCode()

}
