package com.example.trendingtimesjetpack.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trendingtimesjetpack.presentation.auth.screen.login.LoginScreen
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.SignUpScreen
import com.example.trendingtimesjetpack.presentation.news.screen.NewsScreen

@Composable
fun NavGraph(navHostController: NavHostController, startDestination: Any) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<LoginRoute> {
            LoginScreen(
                onNavigateToNews = {
                navHostController.navigate(NewsRoute) {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }
            },
                onNavigateToSignUp = { navHostController.navigate(SignUpRoute) },
                onNavigateToForgotPassword = { navHostController.navigate(ForgotPasswordRoute) }
            )
        }
        composable<NewsRoute> {
            NewsScreen()
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onClickAlreadyHaveAccount = {}
            )
        }
    }
}