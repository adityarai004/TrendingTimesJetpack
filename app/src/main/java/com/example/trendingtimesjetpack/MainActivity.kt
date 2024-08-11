package com.example.trendingtimesjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trendingtimesjetpack.core.navigation.NavGraph
import com.example.trendingtimesjetpack.ui.theme.TrendingTimesJetpackTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            TrendingTimesJetpackTheme {
                navController = rememberNavController()
                NavGraph(navHostController = navController)
            }
        }
    }
}