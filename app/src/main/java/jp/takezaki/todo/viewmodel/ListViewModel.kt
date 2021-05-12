package jp.takezaki.todo.viewmodel

import androidx.lifecycle.*
import jp.takezaki.todo.AppRepository
import jp.takezaki.todo.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ListViewModel(
    private val repository: AppRepository
) : ViewModel() {
    val allItems: LiveData<List<TodoItem>> = repository.allItems.asLiveData()

    fun createNewItem(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(
            TodoItem(
                id = 0, // Insert methods treat 0 as not-set while inserting the item.
                name = name,
                isDone = false,
                creationDateTime = LocalDateTime.now(),
                detailText = "",
                dueDateTime = null,
            )
        )
    }

    fun updateItemName(item: TodoItem, newName: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item.updateName(newName))
        }

    fun updateCheckbox(item: TodoItem, isDone: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item.updateCheckBox(isDone))
        }

    fun updateItemDetailText(item: TodoItem, detailText: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item.updateDetailText(detailText))
        }

    fun updateItemDueDateTime(item: TodoItem, dueDateTime: LocalDateTime?) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(item.updateDueDate(dueDateTime))
        }

    fun deleteItem(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    /// undo remove
    fun restoreDeletedItem(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun deleteAllCompletedItem() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCompleted()
    }

}

class ListViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
