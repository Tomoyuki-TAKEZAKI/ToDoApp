package jp.takezaki.todo

import java.time.LocalDateTime

class TodoItem private constructor(
    val name: String,
    val isDone: Boolean,
    val creationDateTime: LocalDateTime,
) {

    companion object Factory {
        fun getNewItem(name: String): TodoItem =
            TodoItem(name, false, LocalDateTime.now())

        fun getUpdatedItem(item: TodoItem, newName: String): TodoItem =
            TodoItem(newName, item.isDone, item.creationDateTime)

        fun getUpdatedItem(item: TodoItem, isDone: Boolean): TodoItem =
            TodoItem(item.name, isDone, item.creationDateTime)
    }

    override fun toString(): String = "TodoItem(name: $name, isDone: $isDone, dateTime: $creationDateTime)"

    override fun hashCode(): Int = creationDateTime.hashCode()

}
