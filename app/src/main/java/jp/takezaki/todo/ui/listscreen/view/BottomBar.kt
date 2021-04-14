package jp.takezaki.todo.ui.listscreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import jp.takezaki.todo.ui.listscreen.button.MenuButton
import jp.takezaki.todo.ui.listscreen.button.EditListButton
import jp.takezaki.todo.ui.listscreen.button.NewItemButton

@Composable
fun BottomBar() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (menuButton, newItemButton, editListButton) = createRefs()
        Box(
            modifier = Modifier.constrainAs(menuButton) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            MenuButton()
        }
        Box(
            modifier = Modifier.constrainAs(newItemButton) {
                start.linkTo(menuButton.end)
                end.linkTo(editListButton.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            NewItemButton()
        }
        Box(
            modifier = Modifier.constrainAs(editListButton) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) {
            EditListButton()
        }
    }
}
