package jp.takezaki.todo

class TodoItem(private val id: Int, val name: String, val isDone: Boolean) {

    fun updateName(item: TodoItem, newName: String): TodoItem =
        TodoItem(item.id, newName, item.isDone)

    fun toggleCheckBox(item: TodoItem): TodoItem = TodoItem(item.id, item.name, !item.isDone)

}