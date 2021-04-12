package jp.takezaki.todo

class TodoItem(private val id: Int, val name: String, val isDone: Boolean) {

    companion object Factory {
        fun getItemWithUpdatedName(item: TodoItem, newName: String): TodoItem =
            TodoItem(item.id, newName, item.isDone)

        fun getToggledItem(item: TodoItem): TodoItem =
            TodoItem(item.id, item.name, !item.isDone)
    }

}