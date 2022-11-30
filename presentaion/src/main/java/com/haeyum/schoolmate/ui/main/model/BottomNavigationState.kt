package com.haeyum.schoolmate.ui.main.model

sealed class BottomNavigationState {
    object Home : BottomNavigationState()
    object Board : BottomNavigationState()
    object Profile : BottomNavigationState()
}