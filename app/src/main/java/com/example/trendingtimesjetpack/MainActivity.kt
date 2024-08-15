package com.example.trendingtimesjetpack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trendingtimesjetpack.core.navigation.Login
import com.example.trendingtimesjetpack.core.navigation.NavGraph
import com.example.trendingtimesjetpack.core.navigation.News
import com.example.trendingtimesjetpack.domain.use_cases.GetBooleanUseCase
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
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TrendingTimesJetpackTheme {
                navController = rememberNavController()
                val isLoggedIn = mainViewModel.isLoggedIn.collectAsState()
                val startDestination: Any = if (isLoggedIn.value) News else Login
                NavGraph(navHostController = navController, startDestination)
            }
        }
    }
}