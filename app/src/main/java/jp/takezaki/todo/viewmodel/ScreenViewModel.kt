package jp.takezaki.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.takezaki.todo.Screen

class ScreenViewModel : ViewModel() {
    private var _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen

    init {
        setScreen(Screen.ListScreen)
    }

    fun setScreen(screen: Screen) {
        _screen.value = screen
    }

}