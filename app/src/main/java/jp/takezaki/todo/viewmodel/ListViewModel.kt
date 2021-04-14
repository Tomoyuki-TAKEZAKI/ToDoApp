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
        _list.value!!.toMutableList().apply {
            add(TodoItem.getNewItem(name))
            onListModified()
        }
    }

    fun updateItemName(item: TodoItem, newName: String) {
        _list.value!!.toMutableList().apply {
            removeIf { it shouldBeUpdatedBy item }
            add(TodoItem.getItemWithUpdatedName(item, newName))
            sortBy { it.creationDateTime }
            onListModified()
        }
    }

    fun updateItemCheckbox(item: TodoItem, isDone: Boolean) {
        _list.value!!.toMutableList().apply {
            removeIf { it shouldBeUpdatedBy item }
            add(TodoItem.getItemWithUpdatedCheckBox(item, isDone))
            sortBy { it.creationDateTime }
            onListModified()
        }
    }

    fun updateItemDetailText(item: TodoItem, detailText: String) {
        _list.value!!.toMutableList().apply {
            removeIf { it shouldBeUpdatedBy item }
            add(TodoItem.getItemWithUpdatedDetailText(item, detailText))
            sortBy { it.creationDateTime }
            onListModified()
        }
    }

    fun updateItemDueDateTime(item: TodoItem, dueDateTime: LocalDateTime?) {
        _list.value!!.toMutableList().apply {
            removeIf { it shouldBeUpdatedBy item }
            add(TodoItem.getItemWithUpdatedDueDate(item, dueDateTime))
            sortBy { it.creationDateTime }
            onListModified()
        }
    }

    fun removeItem(item: TodoItem) {
        _list.value!!.toMutableList().apply {
            removeIf { it shouldBeUpdatedBy item }
            sortBy { it.creationDateTime }
            onListModified()
        }
    }

    fun removeAllCompletedItem() {
        _list.value!!.toMutableList().apply {
            removeAll { it.isDone }
            onListModified()
        }
    }

    private fun List<TodoItem>.onListModified() {
        setList()
        save()
    }

    private fun List<TodoItem>.setList() {
        _list.value = this
    }

    private fun List<TodoItem>.save() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO save ItemList to DB
        }

        // for debugging
        println("= ".repeat(50))
        forEach { println("saved: item $it") }
    }
}
