package com.example.trendingtimesjetpack.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trendingtimesjetpack.presentation.auth.screen.login.LoginScreen
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.SignUpRoute
import com.example.trendingtimesjetpack.presentation.news.screen.NewsScreen

@Composable
fun NavGraph(navHostController: NavHostController, startDestination: Any) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<LoginRoute>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            LoginScreen(
                onNavigateToNews = {
                navHostController.navigate(NewsRoute) {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }
            },
                onNavigateToSignUp = { navHostController.navigate(SignUpRoute) },
                onNavigateToForgotPassword = { navHostController.navigate(ForgotPasswordRoute) },
            )

        }
        composable<NewsRoute> {
            NewsScreen()
        }
        composable<SignUpRoute>(
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(30, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            SignUpRoute(
                onClickAlreadyHaveAccount = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
