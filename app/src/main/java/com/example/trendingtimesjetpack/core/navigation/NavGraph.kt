package com.example.trendingtimesjetpack.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trendingtimesjetpack.presentation.auth.screen.login.LoginScreen
import com.example.trendingtimesjetpack.presentation.news.screen.NewsScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Login){
        composable<Login> {
            LoginScreen(onNavigateToNews = {
                navHostController.navigate(News){
                    popUpTo(navHostController.graph.id){
                        inclusive = true
                    }
                }
            })
        }
        composable<News> {
            NewsScreen()
        }
    }
}