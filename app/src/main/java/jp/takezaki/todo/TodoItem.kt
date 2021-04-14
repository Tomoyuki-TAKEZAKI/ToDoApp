package jp.takezaki.todo

import java.time.LocalDateTime

data class TodoItem(
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

    infix fun shouldBeUpdatedBy(other: TodoItem) = hashCode() == other.hashCode()

    override fun toString(): String =
        "TodoItem(name: $name, isDone: $isDone, creationDateTime: $creationDateTime)"

    override fun equals(other: Any?): Boolean {
        if (other !is TodoItem) return false

        if (name != other.name) return false
        if (isDone != other.isDone) return false
        if (creationDateTime != other.creationDateTime) return false

        return true
    }

    override fun hashCode(): Int = creationDateTime.hashCode()
}
