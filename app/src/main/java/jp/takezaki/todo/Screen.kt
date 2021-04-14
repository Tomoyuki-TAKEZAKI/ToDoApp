package jp.takezaki.todo

sealed class Screen {
    object ListScreen : Screen()
    data class DetailScreen(val item: TodoItem) : Screen()
}
