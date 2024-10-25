package cl.rutchandia.dogsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.rutchandia.dogsapp.presentation.screens.DetailScreen
import cl.rutchandia.dogsapp.presentation.screens.MainScreen

@Composable
fun DogsNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(
                navController = navController
            )
        }
    }
}