package jp.takezaki.todo

class TodoItem private constructor(val id: Int, val name: String, val isDone: Boolean) {

    companion object Factory {
        fun getNewItem(id: Int, name: String): TodoItem =
            TodoItem(id, name, false)

        fun getItemWithUpdatedName(item: TodoItem, newName: String): TodoItem =
            TodoItem(item.id, newName, item.isDone)

        fun getToggledItem(item: TodoItem): TodoItem =
            TodoItem(item.id, item.name, !item.isDone)
    }

    override fun toString(): String = "TodoItem(id: $id, name: $name, isDone: $isDone)"

}