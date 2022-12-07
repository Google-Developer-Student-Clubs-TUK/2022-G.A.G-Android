/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.ui.main.navigation

sealed class MainNavRoute(val route: String) {
    companion object {
        const val MAIN_ROUTE = "main"
        const val BOARD_ROUTE = "board"
    }

    object Home : MainNavRoute(route = "home")

    object Board : MainNavRoute(route = BOARD_ROUTE) {
        object Subject : MainNavRoute(route = "subject")

        object ArticleList : MainNavRoute(route = "articleList") {
            const val ARGUMENT_SUBJECT_ID = "subjectId"

            fun createRoute(subjectId: String) = route.replace("{$ARGUMENT_SUBJECT_ID}", subjectId)
        }

        object ArticleDetail : MainNavRoute(route = "articleDetail?articleId={articleId}") {
            const val ARGUMENT_ARTICLE_ID = "articleId"

            fun createRoute(articleId: String) = route.replace("{$ARGUMENT_ARTICLE_ID}", articleId)
        }
    }

    object Profile : MainNavRoute(route = "profile")
}