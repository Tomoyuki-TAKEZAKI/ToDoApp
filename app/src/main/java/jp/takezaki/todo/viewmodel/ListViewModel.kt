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

    fun addNewItem(name: String) {
        insert(
            TodoItem(
                0, // Insert methods treat 0 as not-set while inserting the item.
                name,
                false,
                LocalDateTime.now(),
                "",
                null,
            )
        )
    }

    fun updateItemName(item: TodoItem, newName: String) {
        update(item.updateName(newName))
    }

    fun updateCheckbox(item: TodoItem, isDone: Boolean) {
        update(item.updateCheckBox(isDone))
    }

    fun updateItemDetailText(item: TodoItem, detailText: String) {
        update(item.updateDetailText(detailText))
    }

    fun updateItemDueDateTime(item: TodoItem, dueDateTime: LocalDateTime?) {
        update(item.updateDueDate(dueDateTime))
    }

    fun removeItem(item: TodoItem) {
        delete(item)
    }

    /// undo remove
    fun restoreDeletedItem(item: TodoItem) {
        insert(item)
    }

    fun removeAllCompletedItem() {
        // TODO
    }

    private fun insert(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    private fun update(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }

    private fun delete(item: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
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
