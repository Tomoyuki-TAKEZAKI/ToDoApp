package jp.takezaki.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.takezaki.todo.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val _list = MutableLiveData<List<TodoItem>>()
    val list: LiveData<List<TodoItem>> = _list

    init {
        loadItemList()
    }

    private fun loadItemList() {
        _list.value = listOf()

        viewModelScope.launch(Dispatchers.IO) {
            // TODO load data from DB
        }
    }

    fun addItem(name: String) {
        val temp = list.value!!.toMutableList()

        temp.add(TodoItem.getNewItem(name))

        temp.onListModified()
    }

    fun modifyItemName(item: TodoItem, newName: String): TodoItem {
        val temp = list.value!!.toMutableList()

        temp.removeIf { it.hashCode() == item.hashCode() }
        val updatedItem = TodoItem.getUpdatedItem(item, newName)
        temp.add(updatedItem)
        temp.sortBy {
            it.dateTime
        }

        temp.onListModified()
        return updatedItem
    }

    fun updateItemCheckbox(item: TodoItem, isDone: Boolean): TodoItem {
        val temp = list.value!!.toMutableList()

        temp.removeIf { it.hashCode() == item.hashCode() }
        val updatedItem = TodoItem.getUpdatedItem(item, isDone)
        temp.add(updatedItem)
        temp.sortBy {
            it.dateTime
        }

        temp.onListModified()
        return updatedItem
    }

    fun removeItem(item: TodoItem) {
        val temp = list.value!!.toMutableList()

        temp.removeIf {
            it.hashCode() == item.hashCode()
        }
        temp.sortBy {
            it.dateTime
        }

        temp.onListModified()
    }

    private fun List<TodoItem>.onListModified() {
        updateUI()
        save()
    }

    private fun List<TodoItem>.updateUI() {
        _list.value = this
    }

    private fun List<TodoItem>.save() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO save ItemList to DB
        }

        // for debugging
        println("= ".repeat(50))
        forEach { println("saved: item $it, hashcode: ${it.hashCode()}") }
    }

}
