package jp.takezaki.todo

import java.time.LocalDateTime

data class TodoItem constructor(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val creationDateTime: LocalDateTime,
    val detailText: String,
    val dueDateTime: LocalDateTime?,
) {

    fun updateName(newName: String): TodoItem =
        copy(
            id = id,
            name = newName,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime
        )

    fun updateCheckBox(isDone: Boolean): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime
        )

    fun updateDetailText(detailText: String): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime
        )

    fun updateDueDate(dueDateTime: LocalDateTime?): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime
        )

    infix fun shouldBeUpdatedBy(other: TodoItem): Boolean = id == other.id

    // 同値性を定義すれば、mapでそのアイテムだけ更新すれば良い
    // compose内部での差分検知はどうなっている？ RecyclerViewのdiffUtilのような仕組みは？

}
