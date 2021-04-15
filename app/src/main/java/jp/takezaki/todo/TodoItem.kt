package jp.takezaki.todo

import java.time.LocalDateTime

class TodoItem private constructor(
    val name: String,
    val isDone: Boolean,
    val creationDateTime: LocalDateTime,
    val detailText: String,
    val dueDateTime: LocalDateTime?,
) {

    companion object Factory {
        fun getNewItem(name: String): TodoItem =
            TodoItem(name, false, LocalDateTime.now(), "", null)

        fun getItemWithUpdatedName(item: TodoItem, newName: String): TodoItem =
            TodoItem(newName, item.isDone, item.creationDateTime, item.detailText, item.dueDateTime)

        fun getItemWithUpdatedCheckBox(item: TodoItem, isDone: Boolean): TodoItem =
            TodoItem(item.name, isDone, item.creationDateTime, item.detailText, item.dueDateTime)

        fun getItemWithUpdatedDetailText(item: TodoItem, detailText: String): TodoItem =
            TodoItem(item.name, item.isDone, item.creationDateTime, detailText, item.dueDateTime)

        fun getItemWithUpdatedDueDate(item: TodoItem, dueDateTime: LocalDateTime?): TodoItem =
            TodoItem(item.name, item.isDone, item.creationDateTime, item.detailText, dueDateTime)
    }

    infix fun shouldBeUpdatedBy(other: TodoItem): Boolean = creationDateTime == other.creationDateTime
}
