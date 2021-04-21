package jp.takezaki.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.takezaki.todo.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ListViewModel : ViewModel() {
    private val _list = MutableLiveData<List<TodoItem>>()
    val list: LiveData<List<TodoItem>>
        get() = _list

    init {
        loadItemList()
    }

    private fun loadItemList() {
        _list.value = listOf()

        viewModelScope.launch(Dispatchers.IO) {
            // TODO load data from DB
        }
    }

    fun addNewItem(name: String) {
        _list.value!!
            .addNewItem(
                TodoItem(
                    LocalDateTime.now().hashCode(), // TODO ID発番はDBの責務にする
                    name,
                    false,
                    LocalDateTime.now(),
                    "",
                    null,
                )
            )
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun updateItemName(item: TodoItem, newName: String) {
        _list.value!!
            .dropOldItem(item)
            .addNewItem(item.updateName(newName))
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun updateCheckbox(item: TodoItem, isDone: Boolean) {
        _list.value!!
            .dropOldItem(item)
            .addNewItem(item.updateCheckBox(isDone))
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun updateItemDetailText(item: TodoItem, detailText: String) {
        _list.value!!
            .dropOldItem(item)
            .addNewItem(item.updateDetailText(detailText))
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun updateItemDueDateTime(item: TodoItem, dueDateTime: LocalDateTime?) {
        _list.value!!
            .dropOldItem(item)
            .addNewItem(item.updateDueDate(dueDateTime))
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun removeItem(item: TodoItem) {
        _list.value!!
            .dropOldItem(item)
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    /// undo remove
    fun addItem(item: TodoItem) {
        _list.value!!
            .addNewItem(item)
            .sortedByCreationDateTime()
            .updateLiveData()
            .save()
    }

    fun removeAllCompletedItem() {
        _list.value!!
            .dropCompletedItems()
            .updateLiveData()
            .save()
    }

    private fun List<TodoItem>.dropOldItem(item: TodoItem): List<TodoItem> =
        filterNot { it shouldBeUpdatedBy item }

    private fun List<TodoItem>.addNewItem(item: TodoItem): List<TodoItem> =
        toMutableList().apply { add(item) }

    private fun List<TodoItem>.dropCompletedItems(): List<TodoItem> =
        filterNot { it.isDone }

    private fun List<TodoItem>.sortedByCreationDateTime(): List<TodoItem> =
        sortedBy { it.creationDateTime }

    private fun List<TodoItem>.updateLiveData(): List<TodoItem> {
        _list.value = this
        return this
    }

    private fun List<TodoItem>.save(): List<TodoItem> {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO save ItemList to DB
        }

        // for debugging
        println("= ".repeat(50))
        forEach { println("saved: item $it") }

        return this
    }
}
