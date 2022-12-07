/*
 * Created by PangMoo on 2022/12/7
 */

package com.haeyum.schoolmate.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.haeyum.schoolmate.ui.main.navigation.nav_graph.boardNavGraph
import com.haeyum.schoolmate.ui.main.profile.ProfileScreen

@Composable
fun SetupMainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = MainNavRoute.Home.route,
        route = MainNavRoute.MAIN_ROUTE
    ) {
        composable(MainNavRoute.Home.route) {

        }
        composable(MainNavRoute.Profile.route) {
            ProfileScreen()
        }
        boardNavGraph(navController = navHostController)
    }
}