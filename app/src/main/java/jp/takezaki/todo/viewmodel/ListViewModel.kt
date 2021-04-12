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
            // TODO load ItemList from DB
        }

        // for testing
        _list.value = (1..100).map {
            TodoItem(it, "test $it ${"test".repeat(5)}", it % 2 == 0)
        }
    }

    fun updateItem(item: TodoItem) {
        // delete old item and register new item

//        onItemModified()
    }

    fun deleteItem(item: TodoItem) {
        // delete item
        
//        onItemModified()
    }

    private fun onItemModified(newList: List<TodoItem>) {
        list.value?.let {
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