package com.example.trendingtimesjetpack.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trendingtimesjetpack.auth.presentation.screen.login.LoginScreen
import com.example.trendingtimesjetpack.auth.presentation.splash.SplashScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Login){
        composable<Splash> {
            SplashScreen()
        }
        composable<Login> {
            LoginScreen()
        }
    }

}