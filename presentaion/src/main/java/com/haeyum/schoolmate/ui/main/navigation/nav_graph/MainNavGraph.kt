/*
 * Created by PangMoo on 2022/12/7
 */

package com.haeyum.schoolmate.ui.main.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.haeyum.schoolmate.ui.main.navigation.MainNavRoute

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        startDestination = MainNavRoute.Profile.route,
        route = MainNavRoute.MAIN_ROUTE
    ) {
        composable(MainNavRoute.Home.route) {

        }
        composable(MainNavRoute.Profile.route) {

        }
    }
}