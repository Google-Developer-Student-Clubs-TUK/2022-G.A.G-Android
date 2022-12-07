/*
 * Created by PangMoo on 2022/12/7
 */

package com.haeyum.schoolmate.ui.main.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haeyum.schoolmate.ui.main.board.article.detail.ArticleDetailScreen
import com.haeyum.schoolmate.ui.main.board.article.list.ArticleListScreen
import com.haeyum.schoolmate.ui.main.board.subject.SubjectScreen
import com.haeyum.schoolmate.ui.main.navigation.MainNavRoute

fun NavGraphBuilder.boardNavGraph(navController: NavController) {
    navigation(
        startDestination = MainNavRoute.Board.Subject.route,
        route = MainNavRoute.BOARD_ROUTE
    ) {
        composable(MainNavRoute.Board.Subject.route) {
            SubjectScreen(onNavigateToArticleList = {
                navController.navigate(MainNavRoute.Board.ArticleList.createRoute(it))
            })
        }

        composable(MainNavRoute.Board.ArticleList.route) {
            ArticleListScreen(onNavigateToDetail = {
                navController.navigate(MainNavRoute.Board.ArticleDetail.createRoute(it))
            })
        }

        composable(MainNavRoute.Board.ArticleDetail.route) {
            ArticleDetailScreen()
        }
    }
}