package com.my.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.my.github.presentation.Downloaded.DownloadedScreen
import com.my.github.presentation.Nav
import com.my.github.presentation.Search.SearchScreen
import com.my.github.ui.theme.GithubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    installSplashScreen()
    setContent {
      GithubTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Nav.Search::class.java.simpleName) {
          composable(route = Nav.Search::class.java.simpleName) {
            SearchScreen {
              navController.navigate(Nav.Downloaded::class.java.simpleName)
            }
          }
          composable(route = Nav.Downloaded::class.java.simpleName) {
            DownloadedScreen(goBack = navController::popBackStack)
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  GithubTheme {
    Greeting("Android")
  }
}