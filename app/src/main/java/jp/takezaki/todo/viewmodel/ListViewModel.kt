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
        viewModelScope.launch(Dispatchers.IO) {
            // TODO load data from DB
        }

        _list.value = (1..100).map {
            TodoItem.getNewItem(it, "test $it")
        }
    }

    fun addItem(name: String) {
        val id = list.value!!.size
        val temp = list.value!!.toMutableList()
        temp.add(TodoItem.getNewItem(id, name))
        onListModified(temp.toList())
    }

    fun modifyItem(item: TodoItem, newName: String) {
        val temp = list.value!!.toMutableList()
        temp[item.id] = TodoItem.getItemWithUpdatedName(item, newName)
        onListModified(temp.toList())
    }

    fun toggleItem(item: TodoItem) {
        val temp = list.value!!.toMutableList()
        temp[item.id] = TodoItem.getToggledItem(item)
        onListModified(temp.toList())
    }

    fun deleteItem(item: TodoItem) {
        val temp = list.value!!.toMutableList()
        temp.removeAt(item.id)
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
    }

}
