package jp.takezaki.todo

import java.time.LocalDateTime

data class TodoItem private constructor(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val creationDateTime: LocalDateTime,
    val detailText: String,
    val dueDateTime: LocalDateTime?,
) {

    companion object Factory {
        fun getNewItem(name: String): TodoItem =
            TodoItem(
                // DB（永続層）との連携
                // idの一意性（データの生成）について誰が責任を持つのか？
                // c.f. CRUD
                LocalDateTime.now().hashCode(), // 衝突しない保証は？
                name,
                false,
                LocalDateTime.now(),
                "",
                null
            )

        // data class なら以下を copy で表現できる
        // fun Item.updateName など
        fun getItemWithUpdatedName(item: TodoItem, newName: String): TodoItem =
            TodoItem(
                item.id,
                newName,
                item.isDone,
                item.creationDateTime,
                item.detailText,
                item.dueDateTime
            )

        fun getItemWithUpdatedCheckBox(item: TodoItem, isDone: Boolean): TodoItem =
            TodoItem(
                item.id,
                item.name,
                isDone,
                item.creationDateTime,
                item.detailText,
                item.dueDateTime
            )

        fun getItemWithUpdatedDetailText(item: TodoItem, detailText: String): TodoItem =
            TodoItem(
                item.id,
                item.name,
                item.isDone,
                item.creationDateTime,
                detailText,
                item.dueDateTime
            )

        fun getItemWithUpdatedDueDate(item: TodoItem, dueDateTime: LocalDateTime?): TodoItem =
            TodoItem(
                item.id,
                item.name,
                item.isDone,
                item.creationDateTime,
                item.detailText,
                dueDateTime
            )
    }

    infix fun shouldBeUpdatedBy(other: TodoItem): Boolean = id == other.id

    // 同値性を定義すれば、mapでそのアイテムだけ更新すれば良い
    // compose内部での差分検知はどうなっている？ RecyclerViewのdiffUtilのような仕組みは？

}
