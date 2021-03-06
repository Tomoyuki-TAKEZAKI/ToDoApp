package jp.takezaki.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "todo_item")
data class TodoItem constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_done")
    val isDone: Boolean,
    @ColumnInfo(name = "creation_datetime")
    val creationDateTime: LocalDateTime,
    @ColumnInfo(name = "detail_text")
    val detailText: String,
    @ColumnInfo(name = "due_datetime")
    val dueDateTime: LocalDateTime?,
) {

    fun updateName(newName: String): TodoItem =
        copy(
            id = id,
            name = newName,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime,
        )

    fun updateCheckBox(isDone: Boolean): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime,
        )

    fun updateDetailText(detailText: String): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime,
        )

    fun updateDueDate(dueDateTime: LocalDateTime?): TodoItem =
        copy(
            id = id,
            name = name,
            isDone = isDone,
            creationDateTime = creationDateTime,
            detailText = detailText,
            dueDateTime = dueDateTime,
        )

    infix fun shouldBeUpdatedBy(other: TodoItem): Boolean = id == other.id

    // 同値性を定義すれば、mapでそのアイテムだけ更新すれば良い
    // compose内部での差分検知はどうなっている？ RecyclerViewのdiffUtilのような仕組みは？

}
