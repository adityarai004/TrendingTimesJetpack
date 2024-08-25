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
import androidx.navigation.toRoute
import com.example.trendingtimesjetpack.presentation.auth.screen.login.LoginScreen
import com.example.trendingtimesjetpack.presentation.auth.screen.signup.SignUpRoute
import com.example.trendingtimesjetpack.presentation.news.screen.NewsRoute
import com.example.trendingtimesjetpack.presentation.readnews.ReadNewsRoute

@Composable
fun NavGraph(navHostController: NavHostController, startDestination: Any) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<LoginNavigation>(
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
                    navHostController.navigate(NewsNavigation) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToSignUp = { navHostController.navigate(SignUpNavigation) },
                onNavigateToForgotPassword = { navHostController.navigate(ForgotPasswordNavigation) },
            )

        }
        composable<NewsNavigation> {
            NewsRoute(
                onNavigateToBookmarks = {},
                onNavigateToSettings = {},
                onNavigateToReadNews = { newsUrl ->
                    navHostController.navigate(
                        ReadNewsNavigation(
                            newsUrl = newsUrl
                        )
                    )
                })
        }
        composable<SignUpNavigation>(
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

        composable<ReadNewsNavigation> { backStackEntry ->
            val readNewsNavigation: ReadNewsNavigation = backStackEntry.toRoute()
            ReadNewsRoute(newsUrl = readNewsNavigation.newsUrl, backPress = {
                navHostController.popBackStack()
            })
        }
    }
}
