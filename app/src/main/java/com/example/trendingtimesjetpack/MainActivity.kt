package com.example.trendingtimesjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trendingtimesjetpack.core.navigation.LoginNavigation
import com.example.trendingtimesjetpack.core.navigation.NavGraph
import com.example.trendingtimesjetpack.core.navigation.NewsNavigation
import com.example.trendingtimesjetpack.presentation.theme.TrendingTimesJetpackTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isLoading.value
            }
        }
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TrendingTimesJetpackTheme {
                navController = rememberNavController()
                val isLoggedIn = mainViewModel.isLoggedIn.collectAsState()
                val startDestination: Any = if (isLoggedIn.value) NewsNavigation else LoginNavigation
                NavGraph(navHostController = navController, startDestination)
            }
        }
    }
}