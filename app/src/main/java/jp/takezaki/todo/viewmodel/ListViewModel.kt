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
        onListModified(temp.toList())
    }

    fun modifyItemName(item: TodoItem, newName: String) {
        val temp = list.value!!.toMutableList()
        temp.removeIf {
            it.hashCode() == item.hashCode()
        }
        temp.add(TodoItem.getUpdatedItem(item, newName))
        onListModified(temp.toList())
    }

    fun updateItemCheckbox(item: TodoItem, isDone: Boolean) {
        val temp = list.value!!.toMutableList()
        temp.removeIf {
            it.hashCode() == item.hashCode()
        }
        temp.add(TodoItem.getUpdatedItem(item, isDone))
        onListModified(temp.toList())
    }

    fun removeItem(item: TodoItem) {
        val temp = list.value!!.toMutableList()
        temp.removeIf {
            it.hashCode() == item.hashCode()
        }
        onListModified(temp.toList())
    }

    private fun onListModified(newList: List<TodoItem>) {
        newList.let {
            updateList(it)
            saveList(it)
        }
    }

    private fun updateList(newList: List<TodoItem>) {
        _list.value = newList
    }

    private fun saveList(list: List<TodoItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO save ItemList to DB
        }
        // for debugging
        println("= ".repeat(50))
        list.forEach { println("saved: item $it, hashcode: ${it.hashCode()}") }
    }

}
